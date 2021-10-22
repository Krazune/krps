package krazune.krps.game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import krazune.krps.user.User;
import krazune.krps.util.ConnectionFactory;

public class GameDAO
{
	private final ConnectionFactory connectionFactory;

	public GameDAO(ConnectionFactory connectionFactory)
	{
		this.connectionFactory = connectionFactory;
	}

	public boolean insert(Game game) throws SQLException
	{
		try (Connection connection = connectionFactory.createConnection())
		{
			String query = "INSERT INTO games (user_id, user_choice, result) VALUES (?, ?, ?) RETURNING id, creation_date";
			PreparedStatement selectStatement = connection.prepareStatement(query);

			char userChoice = GameChoice.convertToChar(game.getUserChoice());
			char gameResult = GameResult.convertToChar(game.getResult());

			selectStatement.setInt(1, game.getUser().getId());
			selectStatement.setString(2, Character.toString(userChoice));
			selectStatement.setString(3, Character.toString(gameResult));

			ResultSet result = selectStatement.executeQuery();

			result.next();

			int id = result.getInt("id");
			Timestamp creationDate = result.getTimestamp("creation_date");

			game.setId(id);
			game.setCreationDate(creationDate);
		}
		catch (Exception e)
		{
			throw e;
		}

		return false;
	}

	public Game find(int id) throws SQLException
	{
		Game game = null;

		try (Connection connection = connectionFactory.createConnection())
		{
			String query = "SELECT games.id, games.user_choice, games.result, games.creation_date, users.id AS user_id, users.name, users.password_hash, users.creation_date AS user_creation_date FROM games games LEFT JOIN users users ON games.user_id = users.id WHERE games.id = ?";
			PreparedStatement selectStatement = connection.prepareStatement(query);

			selectStatement.setInt(1, id);

			ResultSet result = selectStatement.executeQuery();

			if (result.next())
			{
				int userId = result.getInt("user_id");
				String name = result.getString("name");
				String passwordHash = result.getString("password_hash");
				Timestamp userCreationDate = result.getTimestamp("user_creation_date");

				User user = new User(userId, name, passwordHash, userCreationDate);

				char userChoiceCharacter = result.getString("user_choice").charAt(0);
				char gameResultCharacter = result.getString("result").charAt(0);

				GameChoice userChoice = GameChoice.convertToChoice(userChoiceCharacter);
				GameResult gameResult = GameResult.convertToGameResult(gameResultCharacter);
				GameChoice cpuChoice = Game.getChoiceFromResult(userChoice, gameResult);

				Timestamp creationDate = result.getTimestamp("creation_date");

				game = new Game(id, user, userChoice, cpuChoice, creationDate);
			}
		}
		catch (Exception e)
		{
			throw e;
		}

		return game;
	}

	public List<Game> findAll() throws SQLException
	{
		List<Game> games = new ArrayList<>();

		try (Connection connection = connectionFactory.createConnection())
		{
			String query = "SELECT games.id, games.user_choice, games.result, games.creation_date, users.id AS user_id, users.name, users.password_hash, users.creation_date AS user_creation_date FROM games games LEFT JOIN users users ON games.user_id = users.id";
			PreparedStatement selectStatement = connection.prepareStatement(query);

			ResultSet result = selectStatement.executeQuery();

			while (result.next())
			{
				int userId = result.getInt("user_id");
				String name = result.getString("name");
				String passwordHash = result.getString("password_hash");
				Timestamp userCreationDate = result.getTimestamp("user_creation_date");

				User user = new User(userId, name, passwordHash, userCreationDate);

				int id = result.getInt("id");
				char userChoiceCharacter = result.getString("user_choice").charAt(0);
				char gameResultCharacter = result.getString("result").charAt(0);

				GameChoice userChoice = GameChoice.convertToChoice(userChoiceCharacter);
				GameResult gameResult = GameResult.convertToGameResult(gameResultCharacter);
				GameChoice cpuChoice = Game.getChoiceFromResult(userChoice, gameResult);

				Timestamp creationDate = result.getTimestamp("creation_date");

				Game game = new Game(id, user, userChoice, cpuChoice, creationDate);

				games.add(game);
			}
		}
		catch (Exception e)
		{
			throw e;
		}

		return games;
	}

	public List<Game> findByUser(User user, int limit) throws SQLException
	{
		List<Game> games = new ArrayList<>();

		try (Connection connection = connectionFactory.createConnection())
		{
			String query = "SELECT id, user_choice, result, creation_date FROM games WHERE user_id = ? ORDER BY creation_date DESC LIMIT ?";
			PreparedStatement selectStatement = connection.prepareStatement(query);

			selectStatement.setInt(1, user.getId());
			selectStatement.setInt(2, limit);

			ResultSet result = selectStatement.executeQuery();

			while (result.next())
			{
				int id = result.getInt("id");

				char userChoiceCharacter = result.getString("user_choice").charAt(0);
				char gameResultCharacter = result.getString("result").charAt(0);
				
				GameChoice userChoice = GameChoice.convertToChoice(userChoiceCharacter);
				GameResult gameResult = GameResult.convertToGameResult(gameResultCharacter);
				GameChoice cpuChoice = Game.getChoiceFromResult(userChoice, gameResult);

				Timestamp creationDate = result.getTimestamp("creation_date");

				Game game = new Game(id, user, userChoice, cpuChoice, creationDate);

				games.add(game);
			}
		}
		catch (Exception e)
		{
			throw e;
		}

		return games;
	}

	public boolean update(Game game) throws SQLException
	{
		try (Connection connection = connectionFactory.createConnection())
		{
			String query = "UPDATE games SET user_id = ?, user_choice = ?, result = ? WHERE id = ?";
			PreparedStatement selectStatement = connection.prepareStatement(query);

			char userChoice = GameChoice.convertToChar(game.getUserChoice());
			char gameResult = GameResult.convertToChar(game.getResult());

			selectStatement.setInt(1, game.getUser().getId());
			selectStatement.setString(2, Character.toString(userChoice));
			selectStatement.setString(3, Character.toString(gameResult));
			selectStatement.setInt(4, game.getId());

			int updateCount = selectStatement.executeUpdate();

			if (updateCount == 0)
			{
				return false;
			}
		}
		catch (Exception e)
		{
			throw e;
		}

		return true;
	}

	public boolean delete(Game game) throws SQLException
	{
		try (Connection connection = connectionFactory.createConnection())
		{
			String query = "DELETE FROM games WHERE id = ?";
			PreparedStatement selectStatement = connection.prepareStatement(query);

			selectStatement.setInt(1, game.getId());

			int updateCount = selectStatement.executeUpdate();

			if (updateCount == 0)
			{
				return false;
			}
		}
		catch (Exception e)
		{
			throw e;
		}

		return true;
	}

	public int getPlayerPaperCountByUser(User user) throws Exception
	{
		try (final Connection connection = connectionFactory.createConnection())
		{
			String query = "SELECT COUNT(id) FROM games WHERE user_choice = 'p' AND user_id = ?";
			PreparedStatement selectStatement = connection.prepareStatement(query);

			selectStatement.setInt(1, user.getId());

			ResultSet result = selectStatement.executeQuery();

			result.next();

			return result.getInt(1);
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public int getPlayerRockCount() throws Exception
	{
		try (final Connection connection = connectionFactory.createConnection())
		{
			String query = "SELECT COUNT(id) FROM games WHERE user_choice = 'r'";
			PreparedStatement selectStatement = connection.prepareStatement(query);
			ResultSet result = selectStatement.executeQuery();

			result.next();

			return result.getInt(1);
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public int getPlayerPaperCount() throws Exception
	{
		try (final Connection connection = connectionFactory.createConnection())
		{
			String query = "SELECT COUNT(id) FROM games WHERE user_choice = 'p'";
			PreparedStatement selectStatement = connection.prepareStatement(query);
			ResultSet result = selectStatement.executeQuery();

			result.next();

			return result.getInt(1);
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public int getPlayerScissorsCountByUser(User user) throws Exception
	{
		try (final Connection connection = connectionFactory.createConnection())
		{
			String query = "SELECT COUNT(id) FROM games WHERE user_choice = 's' AND user_id = ?";
			PreparedStatement selectStatement = connection.prepareStatement(query);

			selectStatement.setInt(1, user.getId());

			ResultSet result = selectStatement.executeQuery();

			result.next();

			return result.getInt(1);
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public int getTotalDrawCount() throws Exception
	{
		try (final Connection connection = connectionFactory.createConnection())
		{
			String query = "SELECT COUNT(id) FROM games WHERE result = 'd'";
			PreparedStatement selectStatement = connection.prepareStatement(query);
			ResultSet result = selectStatement.executeQuery();

			result.next();

			return result.getInt(1);
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public int getPlayerRockCountByUser(User user) throws Exception
	{
		try (final Connection connection = connectionFactory.createConnection())
		{
			String query = "SELECT COUNT(id) FROM games WHERE user_choice = 'r' AND user_id = ?";
			PreparedStatement selectStatement = connection.prepareStatement(query);

			selectStatement.setInt(1, user.getId());

			ResultSet result = selectStatement.executeQuery();

			result.next();

			return result.getInt(1);
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public int getTotalGameCountByUser(User user) throws Exception
	{
		try (final Connection connection = connectionFactory.createConnection())
		{
			String query = "SELECT COUNT(id) FROM games WHERE user_id = ?";
			PreparedStatement selectStatement = connection.prepareStatement(query);

			selectStatement.setInt(1, user.getId());

			ResultSet result = selectStatement.executeQuery();

			result.next();

			return result.getInt(1);
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public int getTotalWinCount() throws Exception
	{
		try (final Connection connection = connectionFactory.createConnection())
		{
			String query = "SELECT COUNT(id) FROM games WHERE result = 'w'";
			PreparedStatement selectStatement = connection.prepareStatement(query);
			ResultSet result = selectStatement.executeQuery();

			result.next();

			return result.getInt(1);
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public int getTotalLossCountByUser(User user) throws Exception
	{
		try (final Connection connection = connectionFactory.createConnection())
		{
			String query = "SELECT COUNT(id) FROM games WHERE result = 'l' AND user_id = ?";
			PreparedStatement selectStatement = connection.prepareStatement(query);

			selectStatement.setInt(1, user.getId());

			ResultSet result = selectStatement.executeQuery();

			result.next();

			return result.getInt(1);
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public int getTotalGameCount() throws Exception
	{
		try (final Connection connection = connectionFactory.createConnection())
		{
			String query = "SELECT COUNT(id) FROM games";
			PreparedStatement selectStatement = connection.prepareStatement(query);
			ResultSet result = selectStatement.executeQuery();

			result.next();

			return result.getInt(1);
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public int getPlayerScissorsCount() throws Exception
	{
		try (final Connection connection = connectionFactory.createConnection())
		{
			String query = "SELECT COUNT(id) FROM games WHERE user_choice = 's'";
			PreparedStatement selectStatement = connection.prepareStatement(query);
			ResultSet result = selectStatement.executeQuery();

			result.next();

			return result.getInt(1);
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public int getTotalDrawCountByUser(User user) throws Exception
	{
		try (final Connection connection = connectionFactory.createConnection())
		{
			String query = "SELECT COUNT(id) FROM games WHERE result = 'd' AND user_id = ?";
			PreparedStatement selectStatement = connection.prepareStatement(query);

			selectStatement.setInt(1, user.getId());

			ResultSet result = selectStatement.executeQuery();

			result.next();

			return result.getInt(1);
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public int getTotalLossCount() throws Exception
	{
		try (final Connection connection = connectionFactory.createConnection())
		{
			String query = "SELECT COUNT(id) FROM games WHERE result = 'l'";
			PreparedStatement selectStatement = connection.prepareStatement(query);
			ResultSet result = selectStatement.executeQuery();

			result.next();

			return result.getInt(1);
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public int getTotalWinCountByUser(User user) throws Exception
	{
		try (final Connection connection = connectionFactory.createConnection())
		{
			String query = "SELECT COUNT(id) FROM games WHERE result = 'w' AND user_id = ?";
			PreparedStatement selectStatement = connection.prepareStatement(query);

			selectStatement.setInt(1, user.getId());

			ResultSet result = selectStatement.executeQuery();

			result.next();

			return result.getInt(1);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
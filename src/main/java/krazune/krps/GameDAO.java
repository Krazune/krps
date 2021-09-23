package krazune.krps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
			char userChoice = GameChoice.calculateChoiceCharacter(game.getUserChoice());
			char gameResult = GameResult.calculateResultCharacter(game.getResult());

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
				GameChoice userChoice = GameChoice.calculateChoice(userChoiceCharacter);
				char gameResultCharacter = result.getString("result").charAt(0);
				GameResult gameResult = GameResult.calculateResult(gameResultCharacter);
				GameChoice cpuChoice = Game.calculateGameChoiceFromResult(userChoice, gameResult);
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
				GameChoice userChoice = GameChoice.calculateChoice(userChoiceCharacter);
				char gameResultCharacter = result.getString("result").charAt(0);
				GameResult gameResult = GameResult.calculateResult(gameResultCharacter);
				GameChoice cpuChoice = Game.calculateGameChoiceFromResult(userChoice, gameResult);
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

			char userChoice = GameChoice.calculateChoiceCharacter(game.getUserChoice());
			char gameResult = GameResult.calculateResultCharacter(game.getResult());

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
}
package krazune.krps.game.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import krazune.krps.dao.DaoException;
import krazune.krps.game.Game;
import krazune.krps.game.GameChoice;
import krazune.krps.game.GameOutcome;
import krazune.krps.user.User;

public class PostgreSqlGameDao implements GameDao
{
	private static final String INSERT_QUERY = "INSERT INTO games(user_id, user_choice, computer_choice) VALUES(?, ?, ?) RETURNING id, creation_date";
	private static final String GET_BY_ID_QUERY = "SELECT * FROM games A LEFT JOIN users B ON A.user_id = B.id WHERE A.id = ?";
	private static final String GET_ALL_QUERY = "SELECT * FROM games A LEFT JOIN users B ON A.user_id = B.id ORDER BY A.id";
	private static final String GET_ALL_LIMITED_QUERY = "SELECT * FROM games A LEFT JOIN users B ON A.user_id = B.id ORDER BY A.id LIMIT ? OFFSET ?";
	private static final String GET_USER_GAMES_QUERY = "SELECT * FROM games A LEFT JOIN users B ON A.user_id = B.id WHERE B.id = ? ORDER BY A.id";
	private static final String GET_USER_GAMES_LIMITED_QUERY = "SELECT * FROM games A LEFT JOIN users B ON A.user_id = B.id WHERE B.id = ? ORDER BY A.id LIMIT ? OFFSET ?";
	private static final String UPDATE_QUERY = "UPDATE games SET user_id = ?, user_choice = ?, computer_choice = ? WHERE id = ?";
	private static final String DELETE_QUERY = "DELETE FROM games WHERE id = ?";
	private static final String COUNT_QUERY = "SELECT COUNT(id) FROM games";
	private static final String COUNT_BY_USER_QUERY = "SELECT COUNT(id) FROM games WHERE user_id = ?";
	private static final String COUNT_BY_OUTCOME_QUERY = "SELECT COUNT(id) FROM games WHERE outcome = ?";
	private static final String COUNT_BY_OUTCOME_BY_USER_QUERY = "SELECT COUNT(id) FROM games WHERE outcome = ? AND user_id = ?";
	private static final String COUNT_BY_USER_CHOICE_QUERY = "SELECT COUNT(id) FROM games WHERE user_choice = ?";
	private static final String COUNT_BY_USER_CHOICE_BY_USER_QUERY = "SELECT COUNT(id) FROM games WHERE user_choice = ? AND user_id = ?";
	private static final String COUNT_BY_COMPUTER_CHOICE_QUERY = "SELECT COUNT(id) FROM games WHERE computer_choice = ?";

	private DataSource dataSource;

	public PostgreSqlGameDao(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	@Override
	public boolean insert(Game game) throws DaoException
	{
		if (game == null)
		{
			return false;
		}

		try (Connection connection = dataSource.getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);

			if (game.getUser() == null)
			{
				statement.setNull(1, Types.INTEGER);
			}
			else
			{
				statement.setInt(1, game.getUser().getId());
			}

			statement.setString(2, String.valueOf(game.getUserChoice().getSymbol()));
			statement.setString(3, String.valueOf(game.getComputerChoice().getSymbol()));

			ResultSet result = statement.executeQuery();

			if (!result.next())
			{
				return false;
			}

			game.setId(result.getInt(1));
			game.setCreationDate(result.getTimestamp(2));

			return true;
		}
		catch (SQLException e)
		{
			throw new DaoException(e);
		}
	}

	@Override
	public Game get(int id) throws DaoException
	{
		try (Connection connection = dataSource.getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY);

			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();

			if (result.next())
			{
				return createGameFromResultSet(result);
			}

			return null;
		}
		catch (SQLException e)
		{
			throw new DaoException(e);
		}
	}

	@Override
	public List<Game> getGames() throws DaoException
	{
		try (Connection connection = dataSource.getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY);
			ResultSet result = statement.executeQuery();
			List<Game> games = new ArrayList<>();
			HashMap<Integer, User> userIds = new HashMap<>();

			while (result.next())
			{
				games.add(createGameFromResultSet(result, userIds));
			}

			return games;
		}
		catch (SQLException e)
		{
			throw new DaoException(e);
		}
	}

	@Override
	public List<Game> getGames(int start, int size) throws DaoException
	{
		try (Connection connection = dataSource.getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(GET_ALL_LIMITED_QUERY);

			statement.setInt(1, size);
			statement.setInt(2, start);

			ResultSet result = statement.executeQuery();
			List<Game> games = new ArrayList<>();
			HashMap<Integer, User> userIds = new HashMap<>();

			while (result.next())
			{
				games.add(createGameFromResultSet(result, userIds));
			}

			return games;
		}
		catch (SQLException e)
		{
			throw new DaoException(e);
		}
	}

	@Override
	public List<Game> getUserGames(int id) throws DaoException
	{
		try (Connection connection = dataSource.getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(GET_USER_GAMES_QUERY);

			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();
			List<Game> games = new ArrayList<>();
			HashMap<Integer, User> userIds = new HashMap<>();

			while (result.next())
			{
				games.add(createGameFromResultSet(result, userIds));
			}

			return games;
		}
		catch (SQLException e)
		{
			throw new DaoException(e);
		}
	}

	@Override
	public List<Game> getUserGames(int id, int start, int size) throws DaoException
	{
		try (Connection connection = dataSource.getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(GET_USER_GAMES_LIMITED_QUERY);

			statement.setInt(1, id);
			statement.setInt(2, size);
			statement.setInt(3, start);

			ResultSet result = statement.executeQuery();
			List<Game> games = new ArrayList<>();
			HashMap<Integer, User> userIds = new HashMap<>();

			while (result.next())
			{
				games.add(createGameFromResultSet(result, userIds));
			}

			return games;
		}
		catch (SQLException e)
		{
			throw new DaoException(e);
		}
	}

	@Override
	public List<Game> getUserGames(User user) throws DaoException
	{
		if (user == null)
		{
			return null;
		}

		try (Connection connection = dataSource.getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(GET_USER_GAMES_QUERY);

			statement.setInt(1, user.getId());

			ResultSet result = statement.executeQuery();
			List<Game> games = new ArrayList<>();
			HashMap<Integer, User> userIds = new HashMap<>();

			userIds.put(user.getId(), user);

			while (result.next())
			{
				games.add(createGameFromResultSet(result, userIds));
			}

			return games;
		}
		catch (SQLException e)
		{
			throw new DaoException(e);
		}
	}

	@Override
	public List<Game> getUserGames(User user, int start, int size) throws DaoException
	{
		if (user == null)
		{
			return null;
		}

		try (Connection connection = dataSource.getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(GET_USER_GAMES_LIMITED_QUERY);

			statement.setInt(1, user.getId());
			statement.setInt(2, size);
			statement.setInt(3, start);

			ResultSet result = statement.executeQuery();
			List<Game> games = new ArrayList<>();
			HashMap<Integer, User> userIds = new HashMap<>();

			userIds.put(user.getId(), user);

			while (result.next())
			{
				games.add(createGameFromResultSet(result, userIds));
			}

			return games;
		}
		catch (SQLException e)
		{
			throw new DaoException(e);
		}
	}

	@Override
	public boolean update(Game game) throws DaoException
	{
		if (game == null)
		{
			return false;
		}

		try (Connection connection = dataSource.getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);

			if (game.getUser() == null)
			{
				statement.setNull(1, Types.INTEGER);
			}
			else
			{
				statement.setInt(1, game.getUser().getId());
			}

			statement.setString(2, String.valueOf(game.getUserChoice().getSymbol()));
			statement.setString(3, String.valueOf(game.getComputerChoice().getSymbol()));
			statement.setInt(4, game.getId());

			return statement.executeUpdate() == 1;
		}
		catch (SQLException e)
		{
			throw new DaoException(e);
		}
	}

	@Override
	public boolean delete(int id) throws DaoException
	{
		try (Connection connection = dataSource.getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);

			statement.setInt(1, id);

			return statement.executeUpdate() == 1;
		}
		catch (SQLException e)
		{
			throw new DaoException(e);
		}
	}

	@Override
	public boolean delete(Game game) throws DaoException
	{
		if (game == null)
		{
			return false;
		}

		return delete(game.getId());
	}

	@Override
	public int getGameCount() throws DaoException
	{
		try (Connection connection = dataSource.getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(COUNT_QUERY);
			ResultSet result = statement.executeQuery();

			result.next();

			return result.getInt(1);
		}
		catch (SQLException e)
		{
			throw new DaoException(e);
		}
	}

	@Override
	public int getUserGameCount(int id) throws DaoException
	{
		try (Connection connection = dataSource.getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(COUNT_BY_USER_QUERY);

			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();

			result.next();

			return result.getInt(1);
		}
		catch (SQLException e)
		{
			throw new DaoException(e);
		}
	}

	@Override
	public int getUserGameCount(User user) throws DaoException
	{
		if (user == null)
		{
			return 0;
		}

		return getUserGameCount(user.getId());
	}

	@Override
	public int getOutcomeCount(GameOutcome outcome) throws DaoException
	{
		try (Connection connection = dataSource.getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(COUNT_BY_OUTCOME_QUERY);

			statement.setString(1, String.valueOf(outcome.getSymbol()));

			ResultSet result = statement.executeQuery();

			result.next();

			return result.getInt(1);
		}
		catch (SQLException e)
		{
			throw new DaoException(e);
		}
	}

	@Override
	public int getOutcomeCount(GameOutcome outcome, User user) throws DaoException
	{
		try (Connection connection = dataSource.getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(COUNT_BY_OUTCOME_BY_USER_QUERY);

			statement.setString(1, String.valueOf(outcome.getSymbol()));
			statement.setInt(2, user.getId());

			ResultSet result = statement.executeQuery();

			result.next();

			return result.getInt(1);
		}
		catch (SQLException e)
		{
			throw new DaoException(e);
		}
	}

	@Override
	public int getUserChoiceCount(GameChoice userChoice) throws DaoException
	{
		try (Connection connection = dataSource.getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(COUNT_BY_USER_CHOICE_QUERY);

			statement.setString(1, String.valueOf(userChoice.getSymbol()));

			ResultSet result = statement.executeQuery();

			result.next();

			return result.getInt(1);
		}
		catch (SQLException e)
		{
			throw new DaoException(e);
		}
	}

	@Override
	public int getUserChoiceCount(GameChoice userChoice, User user) throws DaoException
	{
		try (Connection connection = dataSource.getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(COUNT_BY_USER_CHOICE_BY_USER_QUERY);

			statement.setString(1, String.valueOf(userChoice.getSymbol()));
			statement.setInt(2, user.getId());

			ResultSet result = statement.executeQuery();

			result.next();

			return result.getInt(1);
		}
		catch (SQLException e)
		{
			throw new DaoException(e);
		}
	}

	@Override
	public int getComputerChoiceCount(GameChoice computerChoice) throws DaoException
	{
		try (Connection connection = dataSource.getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(COUNT_BY_COMPUTER_CHOICE_QUERY);

			statement.setString(1, String.valueOf(computerChoice.getSymbol()));

			ResultSet result = statement.executeQuery();

			result.next();

			return result.getInt(1);
		}
		catch (SQLException e)
		{
			throw new DaoException(e);
		}
	}

	private Game createGameFromResultSet(ResultSet resultSet) throws DaoException
	{
		try
		{
			return createGameFromResultSet(resultSet, new HashMap<>());
		}
		catch (SQLException e)
		{
			throw new DaoException(e);
		}
	}

	private Game createGameFromResultSet(ResultSet resultSet, Map<Integer, User> userIds) throws SQLException
	{
		int id = resultSet.getInt(1);
		User user = null;

		if (resultSet.getObject(2) != null)
		{
			int userId = resultSet.getInt(2);

			if (!userIds.containsKey(userId))
			{
				String username = resultSet.getString(8);
				String passwordHash = resultSet.getString(9);
				Timestamp userCreationDate = resultSet.getTimestamp(10);

				userIds.put(userId, new User(userId, username, passwordHash, userCreationDate));
			}

			user = userIds.get(userId);
		}

		GameChoice userChoice = GameChoice.getGameChoice(resultSet.getString(3));
		GameChoice computerChoice = GameChoice.getGameChoice(resultSet.getString(4));
		Timestamp creationDate = resultSet.getTimestamp(6);

		return new Game(id, user, userChoice, computerChoice, creationDate);
	}
}

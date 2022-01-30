package krazune.krps.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import krazune.krps.dao.DaoException;
import krazune.krps.user.User;

public class PostgreSqlUserDao implements UserDao
{
	private static final String INSERT_QUERY = "INSERT INTO users(username, password_hash) VALUES(?, ?) RETURNING id, creation_date";
	private static final String GET_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
	private static final String GET_BY_USERNAME_QUERY = "SELECT * FROM users WHERE username = ?";
	private static final String GET_ALL_QUERY = "SELECT * FROM users ORDER BY id";
	private static final String GET_ALL_LIMITED_QUERY = "SELECT * FROM users ORDER BY id LIMIT ? OFFSET ?";
	private static final String UPDATE_QUERY = "UPDATE users SET username = ?, password_hash = ? WHERE id = ?";
	private static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";
	private static final String COUNT_QUERY = "SELECT COUNT(id) FROM users";

	private DataSource dataSource;

	public PostgreSqlUserDao(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	@Override
	public boolean insert(User user) throws DaoException
	{
		if (user == null)
		{
			return false;
		}

		try (Connection connection = dataSource.getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);

			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPasswordHash());

			ResultSet result = statement.executeQuery();

			if (!result.next())
			{
				return false;
			}

			user.setId(result.getInt(1));
			user.setCreationDate(result.getTimestamp(2));

			return true;
		}
		catch (SQLException e)
		{
			throw new DaoException(e);
		}
	}

	@Override
	public User get(int id) throws DaoException
	{
		try (Connection connection = dataSource.getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY);

			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();

			if (result.next())
			{
				return createUserFromResultSet(result);
			}

			return null;
		}
		catch (SQLException e)
		{
			throw new DaoException(e);
		}
	}

	@Override
	public User get(String username) throws DaoException
	{
		try (Connection connection = dataSource.getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(GET_BY_USERNAME_QUERY);

			statement.setString(1, username);

			ResultSet result = statement.executeQuery();

			if (result.next())
			{
				return createUserFromResultSet(result);
			}

			return null;
		}
		catch (SQLException e)
		{
			throw new DaoException(e);
		}
	}

	@Override
	public List<User> getUsers() throws DaoException
	{
		try (Connection connection = dataSource.getConnection())
		{
			List<User> users = new ArrayList<>();
			PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY);
			ResultSet result = statement.executeQuery();

			while (result.next())
			{
				users.add(createUserFromResultSet(result));
			}

			return users;
		}
		catch (SQLException e)
		{
			throw new DaoException(e);
		}
	}

	@Override
	public List<User> getUsers(int start, int size) throws DaoException
	{
		try (Connection connection = dataSource.getConnection())
		{
			List<User> users = new ArrayList<>();
			PreparedStatement statement = connection.prepareStatement(GET_ALL_LIMITED_QUERY);

			statement.setInt(1, size);
			statement.setInt(2, start);

			ResultSet result = statement.executeQuery();

			while (result.next())
			{
				users.add(createUserFromResultSet(result));
			}

			return users;
		}
		catch (SQLException e)
		{
			throw new DaoException(e);
		}
	}

	@Override
	public boolean update(User user) throws DaoException
	{
		if (user == null)
		{
			return false;
		}

		try (Connection connection = dataSource.getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);

			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPasswordHash());
			statement.setInt(3, user.getId());

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
	public boolean delete(User user) throws DaoException
	{
		if (user == null)
		{
			return false;
		}

		try (Connection connection = dataSource.getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);

			statement.setInt(1, user.getId());

			return statement.executeUpdate() == 1;
		}
		catch (SQLException e)
		{
			throw new DaoException(e);
		}
	}

	@Override
	public int getUserCount() throws DaoException
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

	private User createUserFromResultSet(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("id");
		String username = resultSet.getString("username");
		String passwordHash = resultSet.getString("password_hash");
		Timestamp creationDate = resultSet.getTimestamp("creation_date");

		return new User(id, username, passwordHash, creationDate);
	}
}

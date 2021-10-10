package krazune.krps;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserDAO
{
	private final ConnectionFactory connectionFactory;

	public UserDAO(ConnectionFactory connectionFactory)
	{
		this.connectionFactory = connectionFactory;
	}

	public boolean insert(User user) throws SQLException
	{
		try (Connection connection = connectionFactory.createConnection())
		{
			String query = "INSERT INTO users (name, password_hash) VALUES (?, ?) RETURNING id, creation_date";
			PreparedStatement selectStatement = connection.prepareStatement(query);

			selectStatement.setString(1, user.getName());
			selectStatement.setString(2, user.getPasswordHash());

			ResultSet result = selectStatement.executeQuery();

			result.next();

			int id = result.getInt("id");
			Timestamp creationDate = result.getTimestamp("creation_date");

			user.setId(id);
			user.setCreationDate(creationDate);
		}
		catch (Exception e)
		{
			throw e;
		}

		return false;
	}

	public User find(int id) throws SQLException
	{
		User user = null;

		try (Connection connection = connectionFactory.createConnection())
		{
			String query = "SELECT * FROM users WHERE id = ?";
			PreparedStatement selectStatement = connection.prepareStatement(query);

			selectStatement.setInt(1, id);

			ResultSet result = selectStatement.executeQuery();

			if (result.next())
			{
				String name = result.getString("name");
				String passwordHash = result.getString("password_hash");
				Timestamp creationDate = result.getTimestamp("creation_date");

				user = new User(id, name, passwordHash, creationDate);
			}
		}
		catch (Exception e)
		{
			throw e;
		}

		return user;
	}

	public User findByName(String name) throws SQLException
	{
		User user = null;

		try (Connection connection = connectionFactory.createConnection())
		{
			String query = "SELECT * FROM users WHERE name = ?";
			PreparedStatement selectStatement = connection.prepareStatement(query);

			selectStatement.setString(1, name);

			ResultSet result = selectStatement.executeQuery();

			if (result.next())
			{
				int id = result.getInt("id");
				String passwordHash = result.getString("password_hash");
				Timestamp creationDate = result.getTimestamp("creation_date");

				user = new User(id, name, passwordHash, creationDate);
			}
		}
		catch (Exception e)
		{
			throw e;
		}

		return user;
	}

	public List<User> findAll() throws Exception
	{
		List<User> users = new ArrayList<>();

		try (Connection connection = connectionFactory.createConnection())
		{
			String query = "SELECT * FROM users";
			PreparedStatement selectStatement = connection.prepareStatement(query);

			ResultSet result = selectStatement.executeQuery();

			while (result.next())
			{
				int id = result.getInt("id");
				String name = result.getString("name");
				String passwordHash = result.getString("password_hash");
				Timestamp creationDate = result.getTimestamp("creation_date");

				User newUser = new User(id, name, passwordHash, creationDate);

				users.add(newUser);
			}
		}
		catch (Exception e)
		{
			throw e;
		}

		return users;
	}

	public boolean update(User user) throws Exception
	{
		try (Connection connection = connectionFactory.createConnection())
		{
			String query = "UPDATE users SET name = ?, password_hash = ? WHERE id = ?";
			PreparedStatement selectStatement = connection.prepareStatement(query);

			selectStatement.setString(1, user.getName());
			selectStatement.setString(2, user.getPasswordHash());
			selectStatement.setInt(3, user.getId());

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

	public boolean delete(User user) throws Exception
	{
		try (Connection connection = connectionFactory.createConnection())
		{
			String query = "DELETE FROM users WHERE id = ?";
			PreparedStatement selectStatement = connection.prepareStatement(query);

			selectStatement.setInt(1, user.getId());

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

	public User findByLoginInformation(String name, String password) throws SQLException
	{
		User user = findByName(name);

		if (user == null)
		{
			return null;
		}

		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		char[] passwordArray = password.toCharArray();
		boolean validInformation = argon2.verify(user.getPasswordHash(), passwordArray);

		argon2.wipeArray(passwordArray);

		if (!validInformation)
		{
			return null;
		}

		return user;
	}

	public static String getPasswordHash(String password, int saltSize, int hashSize, int iterations, int memory, int parallelism)
	{
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, saltSize, hashSize);
		char[] passwordCharArray = password.toCharArray();
		String hash = argon2.hash(iterations, memory, parallelism, passwordCharArray);

		argon2.wipeArray(passwordCharArray);

		return hash;
	}
}
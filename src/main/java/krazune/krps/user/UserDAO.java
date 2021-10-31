/*
	MIT License

	Copyright (c) 2021 Miguel Sousa

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.
*/
package krazune.krps.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import krazune.krps.util.ConnectionFactory;

public class UserDAO
{
	private final ConnectionFactory connectionFactory;

	public UserDAO(ConnectionFactory connectionFactory)
	{
		this.connectionFactory = connectionFactory;
	}

	public void insert(User user) throws SQLException
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
			String query = "SELECT * FROM users WHERE LOWER(name) = ?";
			PreparedStatement selectStatement = connection.prepareStatement(query);

			selectStatement.setString(1, name.toLowerCase());

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

	public List<User> findAll() throws SQLException
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

	public boolean update(User user) throws SQLException
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

	public boolean delete(User user) throws SQLException
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
}
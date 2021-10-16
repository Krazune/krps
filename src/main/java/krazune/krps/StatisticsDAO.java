package krazune.krps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StatisticsDAO
{
	private final ConnectionFactory connectionFactory;

	public StatisticsDAO(ConnectionFactory connectionFactory)
	{
		this.connectionFactory = connectionFactory;
	}

	public int getTotalGameCount() throws Exception
	{
		try (Connection connection = connectionFactory.createConnection())
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

	public int getTotalWinCount() throws Exception
	{
		try (Connection connection = connectionFactory.createConnection())
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

	public int getTotalLossCount() throws Exception
	{
		try (Connection connection = connectionFactory.createConnection())
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

	public int getTotalDrawCount() throws Exception
	{
		try (Connection connection = connectionFactory.createConnection())
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

	public int getPlayerRockCount() throws Exception
	{
		try (Connection connection = connectionFactory.createConnection())
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
		try (Connection connection = connectionFactory.createConnection())
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

	public int getPlayerScissorsCount() throws Exception
	{
		try (Connection connection = connectionFactory.createConnection())
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

	public int getTotalGameCountByUser(User user) throws Exception
	{
		try (Connection connection = connectionFactory.createConnection())
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

	public int getTotalWinCountByUser(User user) throws Exception
	{
		try (Connection connection = connectionFactory.createConnection())
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

	public int getTotalLossCountByUser(User user) throws Exception
	{
		try (Connection connection = connectionFactory.createConnection())
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

	public int getTotalDrawCountByUser(User user) throws Exception
	{
		try (Connection connection = connectionFactory.createConnection())
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

	public int getPlayerRockCountByUser(User user) throws Exception
	{
		try (Connection connection = connectionFactory.createConnection())
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

	public int getPlayerPaperCountByUser(User user) throws Exception
	{
		try (Connection connection = connectionFactory.createConnection())
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

	public int getPlayerScissorsCountByUser(User user) throws Exception
	{
		try (Connection connection = connectionFactory.createConnection())
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
}
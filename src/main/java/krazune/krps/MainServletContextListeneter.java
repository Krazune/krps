package krazune.krps;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import krazune.krps.dao.PostgreSqlDaoFactory;
import krazune.krps.hash.Argon2IdHashGenerator;

public class MainServletContextListeneter implements ServletContextListener
{
	private static final String POSTGRESQL_DATA_SOURCE_NAME = "java:/comp/env/jdbc/postgres";

	private static final int SALT_SIZE = 16;
	private static final int HASH_SIZE = 32;
	private static final int ITERATIONS = 2;
	private static final int MEMORY = 16384;
	private static final int PARALLELISM = 1;

	@Override
	public void contextInitialized(ServletContextEvent event)
	{
		ServletContext servletContext = event.getServletContext();

		setupDaoFactory(servletContext);
		setupHashGenerator(servletContext);
	}

	private void setupDaoFactory(ServletContext servletContext)
	{
		servletContext.setAttribute("mainDaoFactory", new PostgreSqlDaoFactory(getDataSource()));
	}

	private DataSource getDataSource()
	{
		try
		{
			return (DataSource) new InitialContext().lookup(POSTGRESQL_DATA_SOURCE_NAME);
		}
		catch (NamingException e)
		{
			// To be logged.
			return null;
		}
	}

	private void setupHashGenerator(ServletContext servletContext)
	{
		servletContext.setAttribute("mainHashGenerator", new Argon2IdHashGenerator(SALT_SIZE, HASH_SIZE, ITERATIONS, MEMORY, PARALLELISM));
	}
}

package krazune.krps;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import krazune.krps.dao.PostgreSqlDaoFactory;

public class MainServletContextListeneter implements ServletContextListener
{
	private static final String POSTGRESQL_DATA_SOURCE_NAME = "java:/comp/env/jdbc/postgres";

	@Override
	public void contextInitialized(ServletContextEvent event)
	{
		setupDaoFactory(event.getServletContext());
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
}

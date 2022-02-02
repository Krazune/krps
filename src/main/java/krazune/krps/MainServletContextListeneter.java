/*
 * MIT License
 *
 * Copyright (c) 2022 Miguel Sousa
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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

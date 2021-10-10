package krazune.krps;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginUser extends HttpServlet
{
	PropertiesLoader propertiesLoader;

	public void init() throws ServletException
	{
		propertiesLoader = new PropertiesLoader();

		try
		{
			propertiesLoader.load();
		}
		catch (IOException e)
		{
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username.isEmpty() || password.isEmpty())
		{
			response.sendRedirect("/login");

			return;
		}

		try
		{
			String jdbcUrl = propertiesLoader.getJdbcUrl();
			String jdbcUsername = propertiesLoader.getJdbcUser();
			String jdbcPassword = propertiesLoader.getJdbcPassword();

			ConnectionFactory connectionFactory = new ConnectionFactory(jdbcUrl, jdbcUsername, jdbcPassword);
			UserDAO userDao = new UserDAO(connectionFactory);
			User loginUser = userDao.findByLoginInformation(username, password);

			if (loginUser != null)
			{
				HttpSession session = request.getSession(true);

				session.setAttribute("sessionUserId", loginUser.getId());
				session.setAttribute("sessionUserName", loginUser.getName());

				response.sendRedirect("/");
			}
			else
			{
				response.sendRedirect("/login");
			}
		}
		catch (Exception e)
		{
			throw new ServletException(e);
		}
	}
}

package krazune.krps.user.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import krazune.krps.user.User;
import krazune.krps.user.UserDAO;
import krazune.krps.util.ConnectionFactory;
import krazune.krps.util.PropertiesLoader;

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

				session.setAttribute("sessionUser", loginUser);

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

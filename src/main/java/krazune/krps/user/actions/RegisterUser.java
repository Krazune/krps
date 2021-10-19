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

public class RegisterUser extends HttpServlet
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
		String passwordConfirmation = request.getParameter("password-confirmation");

		if (username.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty())
		{
			response.sendRedirect("/registration");

			return;
		}

		if (!password.equals(passwordConfirmation))
		{
			response.sendRedirect("/registration");

			return;
		}

		try
		{
			String jdbcUrl = propertiesLoader.getJdbcUrl();
			String jdbcUsername = propertiesLoader.getJdbcUser();
			String jdbcPassword = propertiesLoader.getJdbcPassword();

			ConnectionFactory connectionFactory = new ConnectionFactory(jdbcUrl, jdbcUsername, jdbcPassword);
			UserDAO userDao = new UserDAO(connectionFactory);

			if (userDao.findByName(username) != null)
			{
				response.sendRedirect("/registration");

				return;
			}

			int argon2SaltSize = propertiesLoader.getArgon2SaltSize();
			int argon2HashSize = propertiesLoader.getArgon2HashSize();
			int argon2Iterators = propertiesLoader.getArgon2Iterations();
			int argon2Memory = propertiesLoader.getArgon2Memory();
			int argon2Parallelism = propertiesLoader.getArgon2Parallelism();

			String passwordHash = UserDAO.getPasswordHash(password, argon2SaltSize, argon2HashSize, argon2Iterators, argon2Memory, argon2Parallelism);

			User newUser = new User(username, passwordHash);

			userDao.insert(newUser);

			HttpSession session = request.getSession(true);

			session.setAttribute("sessionUser", newUser);

			response.sendRedirect("/");
		}
		catch (Exception e)
		{
			throw new ServletException(e);
		}
	}
}

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
import krazune.krps.util.validators.StringValidator;

public class RegisterUser extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String username = request.getParameter("username");
		StringValidator usernameValidator = new StringValidator();

		usernameValidator.setInput(username);

		usernameValidator.setMinimumSize(3);
		usernameValidator.setMaximumSize(32);
		usernameValidator.setPattern("[a-zA-Z0-9]+");

		usernameValidator.validate();

		String password = request.getParameter("password");
		StringValidator passwordValidator = new StringValidator();

		passwordValidator.setInput(username);

		passwordValidator.setMinimumSize(6);
		passwordValidator.setMaximumSize(128);

		passwordValidator.validate();

		String passwordConfirmation = request.getParameter("password-confirmation");

		if (!usernameValidator.isValid() || !passwordValidator.isValid() || !password.equals(passwordConfirmation))
		{
			response.sendRedirect("/registration");

			return;
		}

		try
		{
			PropertiesLoader propertiesLoader = (PropertiesLoader)request.getAttribute("propertiesLoader");
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

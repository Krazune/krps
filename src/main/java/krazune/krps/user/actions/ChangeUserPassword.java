package krazune.krps.user.actions;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import krazune.krps.user.User;
import krazune.krps.user.UserDAO;
import krazune.krps.util.PropertiesLoader;
import krazune.krps.util.ConnectionFactory;

public class ChangeUserPassword extends HttpServlet
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
		String currentPassword = request.getParameter("current-password");
		String newPassword = request.getParameter("password");
		String newPasswordConfirmation = request.getParameter("password-confirmation");

		if (currentPassword.isEmpty() || newPassword.isEmpty() || newPasswordConfirmation.isEmpty())
		{
			response.sendRedirect("/settings");

			return;
		}

		if (!newPassword.equals(newPasswordConfirmation))
		{
			response.sendRedirect("/settings");

			return;
		}

		HttpSession session = request.getSession(true);
		int sessionUserId = (Integer)session.getAttribute("sessionUserId");

		try
		{
			String jdbcUrl = propertiesLoader.getJdbcUrl();
			String jdbcUsername = propertiesLoader.getJdbcUser();
			String jdbcPassword = propertiesLoader.getJdbcPassword();

			ConnectionFactory connectionFactory = new ConnectionFactory(jdbcUrl, jdbcUsername, jdbcPassword);
			UserDAO userDao = new UserDAO(connectionFactory);

			User sessionUser = userDao.find(sessionUserId);

			Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
			char[] currentPasswordArray = currentPassword.toCharArray();
			boolean correctPassword = argon2.verify(sessionUser.getPasswordHash(), currentPasswordArray);

			argon2.wipeArray(currentPasswordArray);

			if (!correctPassword)
			{
				response.sendRedirect("/settings");

				return;
			}

			int argon2SaltSize = propertiesLoader.getArgon2SaltSize();
			int argon2HashSize = propertiesLoader.getArgon2HashSize();
			int argon2Iterators = propertiesLoader.getArgon2Iterations();
			int argon2Memory = propertiesLoader.getArgon2Memory();
			int argon2Parallelism = propertiesLoader.getArgon2Parallelism();

			String newPasswordHash = UserDAO.getPasswordHash(newPassword, argon2SaltSize, argon2HashSize, argon2Iterators, argon2Memory, argon2Parallelism);

			sessionUser.setPasswordHash(newPasswordHash);

			userDao.update(sessionUser);

			response.sendRedirect("/");
		}
		catch (Exception e)
		{
			throw new ServletException(e);
		}
	}
}

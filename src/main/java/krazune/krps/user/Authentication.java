package krazune.krps.user;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import krazune.krps.util.ConnectionFactory;

public class Authentication
{
	public static User logIn(HttpServletRequest request, ConnectionFactory connectionFactory, String username, String password) throws SQLException
	{
		UserDAO userDao = new UserDAO(connectionFactory);
		User user = userDao.findByName(username);

		if (user == null)
		{
			return null;
		}

		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		char[] passwordArray = password.toCharArray();
		boolean validInformation = argon2.verify(user.getPasswordHash(), passwordArray);

		argon2.wipeArray(passwordArray);

		if (!validInformation)
		{
			return null;
		}

		createUserSession(request, user);

		return user;
	}

	public static void createUserSession(HttpServletRequest request, User user)
	{
		HttpSession session = request.getSession(true);

		session.setAttribute("sessionUser", user);
	}

	public static User getSessionUser(HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);

		if (session == null)
		{
			return null;
		}

		return (User)session.getAttribute("sessionUser");
	}

	public static void logOut(HttpSession session)
	{
		if (session != null)
		{
			session.removeAttribute("sessionUser");
		}
	}
}
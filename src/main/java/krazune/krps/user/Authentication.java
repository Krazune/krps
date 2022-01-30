package krazune.krps.user;

import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import krazune.krps.dao.DaoException;
import krazune.krps.hash.HashGenerator;
import krazune.krps.user.dao.UserDao;

public class Authentication
{
	public static User authenticateUser(HttpSession session, UserDao userDao, HashGenerator hashGenerator, String username, String password) throws SQLException
	{
		User user = null;
		try
		{
			user = userDao.get(username);
		}
		catch (DaoException e)
		{
			throw new SQLException(e);
		}

		if (user == null)
		{
			return null;
		}

		if (!hashGenerator.verify(password, user.getPasswordHash()))
		{
			return null;
		}

		createUserSession(session, user);

		return user;
	}

	public static void createUserSession(HttpSession session, User user)
	{
		session.setAttribute("sessionUser", user);
	}

	public static void destroyUserSession(HttpSession session)
	{
		if (session == null)
		{
			return;
		}

		session.removeAttribute("sessionUser");
	}

	public static User getSessionUser(HttpSession session)
	{
		if (session == null)
		{
			return null;
		}

		return (User)session.getAttribute("sessionUser");
	}
}

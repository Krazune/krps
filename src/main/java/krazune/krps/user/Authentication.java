package krazune.krps.user;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import krazune.krps.util.ConnectionFactory;

public class Authentication
{
	public static User logIn(HttpServletRequest request, ConnectionFactory connectionFactory, String username, String password) throws SQLException
	{
		UserDAO userDao = new UserDAO(connectionFactory);
		User loginUser = userDao.findByLoginInformation(username, password);

		if (loginUser == null)
		{
			return null;
		}

		createUserSession(request, loginUser);

		return loginUser;
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
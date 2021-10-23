package krazune.krps.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Authentication
{
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
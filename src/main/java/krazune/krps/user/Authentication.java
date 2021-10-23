package krazune.krps.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Authentication
{
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
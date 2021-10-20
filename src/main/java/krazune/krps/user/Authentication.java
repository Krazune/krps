package krazune.krps.user;

import javax.servlet.http.HttpSession;

public class Authentication
{
	public static void logOut(HttpSession session)
	{
		if (session != null)
		{
			session.removeAttribute("sessionUser");
		}
	}
}
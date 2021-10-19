package krazune.krps.user.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutUser extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession(false);

		if (session != null)
		{
			session.removeAttribute("sessionUser");
		}

		response.sendRedirect("/");
	}
}

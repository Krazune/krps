package krazune.krps.user.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationPageServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
	}
}

package krazune.krps.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InformationPageServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.getRequestDispatcher("/WEB-INF/jsp/information.jsp").forward(request, response);
	}
}

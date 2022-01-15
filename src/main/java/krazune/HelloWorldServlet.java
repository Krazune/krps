package krazune;

import java.io.IOException;
import java.time.Instant;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setAttribute("helloWorldDate", Instant.now());
		request.getRequestDispatcher("/WEB-INF/jsp/hello-world.jsp").forward(request, response);
	}
}

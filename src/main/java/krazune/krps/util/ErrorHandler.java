package krazune.krps.util;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorHandler extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		Throwable throwable = (Throwable)request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

		logError(throwable);

		request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		Throwable throwable = (Throwable)request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

		logError(throwable);

		request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
	}

	private void logError(Throwable throwable)
	{
		if (throwable == null)
		{
			return;
		}

		getServletContext().log(throwable.toString());

		StackTraceElement[] elements = throwable.getStackTrace();

		for (StackTraceElement element : elements)
		{
			getServletContext().log(element.toString());
		}
	}
}

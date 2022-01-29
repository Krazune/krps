package krazune.krps.user.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import krazune.krps.dao.DaoFactory;
import krazune.krps.hash.HashGenerator;
import krazune.krps.user.Authentication;
import krazune.krps.user.User;
import krazune.krps.user.dao.UserDao;

public class RegistrationPageServlet extends HttpServlet
{
	private UserDao userDao;
	private HashGenerator hashGenerator;

	@Override
	public void init() throws ServletException
	{
		userDao = ((DaoFactory) getServletContext().getAttribute("mainDaoFactory")).createUserDao();
		hashGenerator = (HashGenerator) getServletContext().getAttribute("mainHashGenerator");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		setupNavigationLinks(request);

		request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		setupNavigationLinks(request);

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String passwordConfirmation = request.getParameter("password-confirmation");

		// Validate input.

		User newUser = new User(username, hashGenerator.generate(password));

		try
		{
			userDao.insert(newUser);
		}
		catch (SQLException e)
		{
			throw new ServletException(e);
		}

		Authentication.createUserSession(request.getSession(), newUser);
		response.sendRedirect(request.getContextPath() + "/");
	}

	private void setupNavigationLinks(HttpServletRequest request)
	{
		boolean authenticated = Authentication.getSessionUser(request.getSession()) != null;

		request.setAttribute("showHomeLink", true);
		request.setAttribute("showLoginLink", !authenticated);
		request.setAttribute("showRegistrationLink", false);
		request.setAttribute("showStatisticsLink", true);
		request.setAttribute("showInformationLink", true);
		request.setAttribute("showSettingsLink", authenticated);
		request.setAttribute("showLogoutLink", authenticated);
	}

	private void invalidLoginRefresh(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setAttribute("previousUsername", request.getParameter("username"));
		request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
	}
}

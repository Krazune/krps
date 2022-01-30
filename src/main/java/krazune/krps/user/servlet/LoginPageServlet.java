package krazune.krps.user.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import krazune.krps.dao.DaoFactory;
import krazune.krps.hash.HashGenerator;
import krazune.krps.user.Authentication;
import krazune.krps.user.User;
import krazune.krps.user.dao.UserDao;
import krazune.krps.validation.EmptyStringValidatorManager;
import krazune.krps.validation.ValidationException;

public class LoginPageServlet extends HttpServlet
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

		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		setupNavigationLinks(request);

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (!validate(request, username, password))
		{
			invalidLoginRefresh(request, response);

			return;
		}

		if (authenticateUser(request.getSession(), username, password) == null)
		{
			request.setAttribute("loginErrorMessage", "Invalid account information.");

			invalidLoginRefresh(request, response);

			return;
		}

		response.sendRedirect(request.getContextPath() + "/");
	}

	private void setupNavigationLinks(HttpServletRequest request)
	{
		boolean authenticated = Authentication.getSessionUser(request.getSession()) != null;

		request.setAttribute("showHomeLink", true);
		request.setAttribute("showLoginLink", false);
		request.setAttribute("showRegistrationLink", !authenticated);
		request.setAttribute("showStatisticsLink", true);
		request.setAttribute("showInformationLink", true);
		request.setAttribute("showSettingsLink", authenticated);
		request.setAttribute("showLogoutLink", authenticated);
	}

	private boolean validate(HttpServletRequest request, String username, String password) throws ServletException
	{
		try
		{
			EmptyStringValidatorManager usernameValidatorManager = new EmptyStringValidatorManager("username");
			List<String> usernameErrorMessages = usernameValidatorManager.validate(username);

			request.setAttribute("usernameErrorMessages", usernameErrorMessages);

			EmptyStringValidatorManager passwordValidatorManager = new EmptyStringValidatorManager("password");
			List<String> passwordErrorMessages = passwordValidatorManager.validate(password);

			request.setAttribute("passwordErrorMessages", passwordErrorMessages);

			return usernameErrorMessages.isEmpty() && passwordErrorMessages.isEmpty();
		}
		catch (ValidationException e)
		{
			throw new ServletException(e);
		}
	}

	private User authenticateUser(HttpSession session, String username, String password) throws ServletException
	{
		try
		{
			return Authentication.authenticateUser(session, userDao, hashGenerator, username, password);
		}
		catch (SQLException e)
		{
			throw new ServletException(e);
		}
	}

	private void invalidLoginRefresh(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setAttribute("previousUsername", request.getParameter("username"));
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}
}

/*
 * MIT License
 *
 * Copyright (c) 2022 Miguel Sousa
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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

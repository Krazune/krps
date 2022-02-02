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
package krazune.krps.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import krazune.krps.dao.DaoException;
import krazune.krps.dao.DaoFactory;
import krazune.krps.hash.HashGenerator;
import krazune.krps.user.Authentication;
import krazune.krps.user.User;
import krazune.krps.user.dao.UserDao;
import krazune.krps.user.validation.PasswordConfirmationValidatorManager;
import krazune.krps.user.validation.PasswordValidatorManager;
import krazune.krps.validation.EmptyStringValidatorManager;
import krazune.krps.validation.ValidationException;

public class SettingsPageServlet extends HttpServlet
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

		request.getRequestDispatcher("/WEB-INF/jsp/settings.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		setupNavigationLinks(request);

		String currentPassword = request.getParameter("current-password");
		String newPassword = request.getParameter("new-password");
		String newPasswordConfirmation = request.getParameter("new-password-confirmation");

		if (!validate(request, currentPassword, newPassword, newPasswordConfirmation))
		{
			refresh(request, response);

			return;
		}

		HttpSession session = request.getSession();
		String username = Authentication.getSessionUser(session).getUsername();
		User sessionUser;

		try
		{
			sessionUser = Authentication.authenticateUser(session, userDao, hashGenerator, username, currentPassword);
		}
		catch (SQLException e)
		{
			throw new ServletException(e);
		}

		if (sessionUser == null)
		{
			request.setAttribute("changePasswordErrorMessage", "Invalid password information.");
			refresh(request, response);

			return;
		}

		sessionUser.setPasswordHash(hashGenerator.generate(newPassword));

		try
		{
			userDao.update(sessionUser);
		}
		catch (DaoException e)
		{
			throw new ServletException(e);
		}

		Authentication.createUserSession(session, sessionUser);

		request.setAttribute("changePasswordSuccessMessage", "The password was changed.");
		refresh(request, response);
	}

	private void setupNavigationLinks(HttpServletRequest request)
	{
		boolean authenticated = Authentication.getSessionUser(request.getSession()) != null;

		request.setAttribute("showHomeLink", true);
		request.setAttribute("showLoginLink", !authenticated);
		request.setAttribute("showRegistrationLink", !authenticated);
		request.setAttribute("showStatisticsLink", true);
		request.setAttribute("showInformationLink", true);
		request.setAttribute("showSettingsLink", false);
		request.setAttribute("showLogoutLink", authenticated);
	}

	private boolean validate(HttpServletRequest request, String currentPassword, String newPassword, String newPasswordConfirmation) throws ServletException
	{
		try
		{
			EmptyStringValidatorManager currentPasswordValidatorManager = new EmptyStringValidatorManager("current password");
			List<String> currentPasswordErrorMessages = currentPasswordValidatorManager.validate(currentPassword);

			request.setAttribute("currentPasswordErrorMessages", currentPasswordErrorMessages);

			PasswordValidatorManager passwordValidatorManager = new PasswordValidatorManager();
			List<String> passwordErrorMessages = passwordValidatorManager.validate(newPassword);

			request.setAttribute("newPasswordErrorMessages", passwordErrorMessages);

			PasswordConfirmationValidatorManager passwordConfirmationValidatorManager = new PasswordConfirmationValidatorManager(newPassword);
			List<String> passwordConfirmationErrorMessages = passwordConfirmationValidatorManager.validate(newPasswordConfirmation);

			request.setAttribute("newPasswordConfirmationErrorMessages", passwordConfirmationErrorMessages);

			return currentPasswordErrorMessages.isEmpty() && passwordErrorMessages.isEmpty() && passwordConfirmationErrorMessages.isEmpty();
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

	private void refresh(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.getRequestDispatcher("/WEB-INF/jsp/settings.jsp").forward(request, response);
	}
}

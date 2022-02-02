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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import krazune.krps.dao.DaoException;
import krazune.krps.dao.DaoFactory;
import krazune.krps.hash.HashGenerator;
import krazune.krps.user.Authentication;
import krazune.krps.user.User;
import krazune.krps.user.dao.UserDao;
import krazune.krps.user.validation.PasswordConfirmationValidatorManager;
import krazune.krps.user.validation.PasswordValidatorManager;
import krazune.krps.user.validation.UsernameValidatorManager;
import krazune.krps.validation.ValidationException;

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

		if (!validate(request, username, password, passwordConfirmation))
		{
			invalidLoginRefresh(request, response);

			return;
		}

		User newUser = new User(username, hashGenerator.generate(password));

		try
		{
			userDao.insert(newUser);
		}
		catch (DaoException e)
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

	private boolean validate(HttpServletRequest request, String username, String password, String passwordConfirmation) throws ServletException
	{
		try
		{
			UsernameValidatorManager usernameValidatorManager = new UsernameValidatorManager(userDao);
			List<String> usernameErrorMessages = usernameValidatorManager.validate(username);

			request.setAttribute("usernameErrorMessages", usernameErrorMessages);

			PasswordValidatorManager passwordValidatorManager = new PasswordValidatorManager();
			List<String> passwordErrorMessages = passwordValidatorManager.validate(password);

			request.setAttribute("passwordErrorMessages", passwordErrorMessages);

			PasswordConfirmationValidatorManager passwordConfirmationValidatorManager = new PasswordConfirmationValidatorManager(password);
			List<String> passwordConfirmationErrorMessages = passwordConfirmationValidatorManager.validate(passwordConfirmation);

			request.setAttribute("passwordConfirmationErrorMessages", passwordConfirmationErrorMessages);

			return usernameErrorMessages.isEmpty() && passwordErrorMessages.isEmpty() && passwordConfirmationErrorMessages.isEmpty();
		}
		catch (ValidationException e)
		{
			throw new ServletException(e);
		}
	}

	private void invalidLoginRefresh(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setAttribute("previousUsername", request.getParameter("username"));
		request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
	}
}

/*
	MIT License

	Copyright (c) 2021 Miguel Sousa

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.
*/
package krazune.krps.util.pagecontrollers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import krazune.krps.user.Authentication;
import krazune.krps.user.User;
import krazune.krps.user.UserDAO;
import krazune.krps.util.ConnectionFactory;
import krazune.krps.util.PropertiesLoader;
import krazune.krps.util.validators.StringValidator;
import krazune.krps.util.validators.StringValidatorError;

public class SettingsPageController extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.getRequestDispatcher("/WEB-INF/jsp/settings.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String currentPassword = request.getParameter("current-password");

		String newPassword = request.getParameter("password");
		Set<StringValidatorError> newPasswordErrors = validateNewPasswordInput(newPassword);

		String newPasswordConfirmation = request.getParameter("password-confirmation");
		Set<StringValidatorError> newPasswordConfirmationErrors = validateNewPasswordConfirmationInput(newPasswordConfirmation, newPassword);

		if (!newPasswordErrors.isEmpty() || !newPasswordConfirmationErrors.isEmpty())
		{
			List<String> newPasswordErrorMessages = getNewPasswordErrorMessages(newPasswordErrors);
			List<String> newPasswordConfirmationErrorMessages = getNewPasswordConfirmationErrorMessages(newPasswordConfirmationErrors);

			request.setAttribute("newPasswordErrorMessages", newPasswordErrorMessages);
			request.setAttribute("newPasswordConfirmationErrorMessages", newPasswordConfirmationErrorMessages);

			request.getRequestDispatcher("/WEB-INF/jsp/settings.jsp").forward(request, response);

			return;
		}

		try
		{
			User sessionUser = Authentication.getSessionUser(request);

			if (!Authentication.validPassword(sessionUser, currentPassword))
			{
				request.setAttribute("accountErrorMessage", "The current password is invalid.");
				request.getRequestDispatcher("/WEB-INF/jsp/settings.jsp").forward(request, response);

				return;
			}

			PropertiesLoader propertiesLoader = (PropertiesLoader)request.getAttribute("propertiesLoader");
			String jdbcUrl = propertiesLoader.getJdbcUrl();
			String jdbcUsername = propertiesLoader.getJdbcUser();
			String jdbcPassword = propertiesLoader.getJdbcPassword();

			int argon2SaltSize = propertiesLoader.getArgon2SaltSize();
			int argon2HashSize = propertiesLoader.getArgon2HashSize();
			int argon2Iterators = propertiesLoader.getArgon2Iterations();
			int argon2Memory = propertiesLoader.getArgon2Memory();
			int argon2Parallelism = propertiesLoader.getArgon2Parallelism();

			String newPasswordHash = Authentication.getPasswordHash(newPassword, argon2SaltSize, argon2HashSize, argon2Iterators, argon2Memory, argon2Parallelism);

			sessionUser.setPasswordHash(newPasswordHash);

			ConnectionFactory connectionFactory = new ConnectionFactory(jdbcUrl, jdbcUsername, jdbcPassword);
			UserDAO userDao = new UserDAO(connectionFactory);

			userDao.update(sessionUser);

			response.sendRedirect("/");
		}
		catch (SQLException e)
		{
			throw new ServletException(e);
		}
	}

	private Set<StringValidatorError> validateNewPasswordInput(String password)
	{
		StringValidator passwordValidator = new StringValidator();

		passwordValidator.setInput(password);

		passwordValidator.setMinimumSize(6);
		passwordValidator.setMaximumSize(128);

		passwordValidator.validate();

		return passwordValidator.getErrors();
	}

	private Set<StringValidatorError> validateNewPasswordConfirmationInput(String passwordConfirmation, String password)
	{
		StringValidator passwordConfirmationValidator = new StringValidator();

		passwordConfirmationValidator.setInput(passwordConfirmation);

		passwordConfirmationValidator.setPattern("\\Q" + password + "\\E"); // This is not efficient, but it's not critical.

		passwordConfirmationValidator.validate();

		return passwordConfirmationValidator.getErrors();
	}

	private List<String> getNewPasswordErrorMessages(Set<StringValidatorError> errorSet)
	{
		List<String> messages = new ArrayList<>();

		if (errorSet.contains(StringValidatorError.NULL) || errorSet.contains(StringValidatorError.TOO_SHORT))
		{
			messages.add("The new password is too short.");
		}
		else if (errorSet.contains(StringValidatorError.TOO_LONG))
		{
			messages.add("The new password is too long.");
		}

		return messages;
	}

	private List<String> getNewPasswordConfirmationErrorMessages(Set<StringValidatorError> errorSet)
	{
		List<String> messages = new ArrayList<>();

		if (errorSet.contains(StringValidatorError.NO_PATTERN_MATCH))
		{
			messages.add("The passwords aren't equal.");
		}

		return messages;
	}
}

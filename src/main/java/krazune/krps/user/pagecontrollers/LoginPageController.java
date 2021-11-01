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
package krazune.krps.user.pagecontrollers;

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
import krazune.krps.util.ConnectionFactory;
import krazune.krps.util.PropertiesLoader;
import krazune.krps.util.validators.StringValidator;
import krazune.krps.util.validators.StringValidatorError;

public class LoginPageController extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String username = request.getParameter("username");
		Set<StringValidatorError> usernameErrors = validateUsernameInput(username);

		String password = request.getParameter("password");
		Set<StringValidatorError> passwordErrors = validatePasswordInput(password);

		if (!usernameErrors.isEmpty() || !passwordErrors.isEmpty())
		{
			List<String> usernameErrorMessages = getUsernameErrorMessages(usernameErrors);
			List<String> passwordErrorMessages = getPasswordErrorMessages(passwordErrors);

			request.setAttribute("usernameErrorMessages", usernameErrorMessages);
			request.setAttribute("passwordErrorMessages", passwordErrorMessages);

			if (username != null)
			{
				request.setAttribute("previousUsernameInput", username);
			}

			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);

			return;
		}

		try
		{
			PropertiesLoader propertiesLoader = (PropertiesLoader)request.getAttribute("propertiesLoader");
			String jdbcUrl = propertiesLoader.getJdbcUrl();
			String jdbcUsername = propertiesLoader.getJdbcUser();
			String jdbcPassword = propertiesLoader.getJdbcPassword();

			ConnectionFactory connectionFactory = new ConnectionFactory(jdbcUrl, jdbcUsername, jdbcPassword);
			User loginUser = Authentication.logIn(request, connectionFactory, username, password);

			if (loginUser != null)
			{
				response.sendRedirect("/");

				return;
			}

			request.setAttribute("accountErrorMessage", "Invalid login information.");

			request.setAttribute("previousUsernameInput", username);

			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		}
		catch (SQLException e)
		{
			throw new ServletException(e);
		}
	}

	private Set<StringValidatorError> validateUsernameInput(String username)
	{
		StringValidator usernameValidator = new StringValidator();

		usernameValidator.setInput(username);

		usernameValidator.setMinimumSize(1);

		usernameValidator.validate();

		return usernameValidator.getErrors();
	}

	private Set<StringValidatorError> validatePasswordInput(String password)
	{
		StringValidator passwordValidator = new StringValidator();

		passwordValidator.setInput(password);

		passwordValidator.setMinimumSize(1);

		passwordValidator.validate();

		return passwordValidator.getErrors();
	}

	private List<String> getUsernameErrorMessages(Set<StringValidatorError> errorSet)
	{
		List<String> messages = new ArrayList<>();

		if (errorSet.contains(StringValidatorError.NULL) || errorSet.contains(StringValidatorError.TOO_SHORT))
		{
			messages.add("The username field cannot be empty.");
		}

		return messages;
	}

	private List<String> getPasswordErrorMessages(Set<StringValidatorError> errorSet)
	{
		List<String> messages = new ArrayList<>();

		if (errorSet.contains(StringValidatorError.NULL) || errorSet.contains(StringValidatorError.TOO_SHORT))
		{
			messages.add("The password field cannot be empty.");
		}

		return messages;
	}
}

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
import krazune.krps.user.UserDAO;
import krazune.krps.util.ConnectionFactory;
import krazune.krps.util.PropertiesLoader;
import krazune.krps.util.validators.StringValidator;
import krazune.krps.util.validators.StringValidatorError;

public class RegistrationPageController extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String username = request.getParameter("username");
		Set<StringValidatorError> usernameErrors = validateUsernameInput(username);

		String password = request.getParameter("password");
		Set<StringValidatorError> passwordErrors = validatePasswordInput(password);

		String passwordConfirmation = request.getParameter("password-confirmation");
		Set<StringValidatorError> passwordConfirmationErrors = validatePasswordConfirmationInput(passwordConfirmation, password);

		if (!usernameErrors.isEmpty() || !passwordErrors.isEmpty() || !passwordConfirmationErrors.isEmpty())
		{
			List<String> usernameErrorMessages = getUsernameErrorMessages(usernameErrors);
			List<String> passwordErrorMessages = getPasswordErrorMessages(passwordErrors);
			List<String> passwordConfirmationErrorMessages = getPasswordConfirmationErrorMessages(passwordConfirmationErrors);

			request.setAttribute("usernameErrorMessages", usernameErrorMessages);
			request.setAttribute("passwordErrorMessages", passwordErrorMessages);
			request.setAttribute("passwordConfirmationErrorMessages", passwordConfirmationErrorMessages);

			if (username != null)
			{
				request.setAttribute("previousUsernameInput", username);
			}

			request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);

			return;
		}

		try
		{
			PropertiesLoader propertiesLoader = (PropertiesLoader)request.getAttribute("propertiesLoader");
			String jdbcUrl = propertiesLoader.getJdbcUrl();
			String jdbcUsername = propertiesLoader.getJdbcUser();
			String jdbcPassword = propertiesLoader.getJdbcPassword();

			ConnectionFactory connectionFactory = new ConnectionFactory(jdbcUrl, jdbcUsername, jdbcPassword);
			UserDAO userDao = new UserDAO(connectionFactory);

			if (userDao.findByName(username) != null)
			{
				request.setAttribute("previousUsernameInput", username);
				request.setAttribute("accountErrorMessage", "The username is already in use.");
				request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);

				return;
			}

			int argon2SaltSize = propertiesLoader.getArgon2SaltSize();
			int argon2HashSize = propertiesLoader.getArgon2HashSize();
			int argon2Iterators = propertiesLoader.getArgon2Iterations();
			int argon2Memory = propertiesLoader.getArgon2Memory();
			int argon2Parallelism = propertiesLoader.getArgon2Parallelism();

			String passwordHash = Authentication.getPasswordHash(password, argon2SaltSize, argon2HashSize, argon2Iterators, argon2Memory, argon2Parallelism);

			User newUser = new User(username, passwordHash);

			userDao.insert(newUser);

			Authentication.createUserSession(request, newUser);

			response.sendRedirect("/");
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

		usernameValidator.setMinimumSize(3);
		usernameValidator.setMaximumSize(32);
		usernameValidator.setPattern("[a-zA-Z0-9]+");

		usernameValidator.validate();

		return usernameValidator.getErrors();
	}

	private Set<StringValidatorError> validatePasswordInput(String password)
	{
		StringValidator passwordValidator = new StringValidator();

		passwordValidator.setInput(password);

		passwordValidator.setMinimumSize(6);
		passwordValidator.setMaximumSize(128);

		passwordValidator.validate();

		return passwordValidator.getErrors();
	}

	private Set<StringValidatorError> validatePasswordConfirmationInput(String passwordConfirmation, String password)
	{
		StringValidator passwordConfirmationValidator = new StringValidator();

		passwordConfirmationValidator.setInput(passwordConfirmation);

		passwordConfirmationValidator.setMinimumSize(6);
		passwordConfirmationValidator.setMaximumSize(128);
		passwordConfirmationValidator.setPattern("\\Q" + password + "\\E"); // This is not efficient, but it's not critical.

		passwordConfirmationValidator.validate();

		return passwordConfirmationValidator.getErrors();
	}

	private List<String> getUsernameErrorMessages(Set<StringValidatorError> errorSet)
	{
		List<String> messages = new ArrayList<>();

		if (errorSet.contains(StringValidatorError.NULL) || errorSet.contains(StringValidatorError.TOO_SHORT))
		{
			messages.add("The username is too short.");
		}
		else if (errorSet.contains(StringValidatorError.TOO_LONG))
		{
			messages.add("The username is too long.");
		}

		if (errorSet.contains(StringValidatorError.NO_PATTERN_MATCH))
		{
			messages.add("The username has invalid characters.");
		}

		return messages;
	}

	private List<String> getPasswordErrorMessages(Set<StringValidatorError> errorSet)
	{
		List<String> messages = new ArrayList<>();

		if (errorSet.contains(StringValidatorError.NULL) || errorSet.contains(StringValidatorError.TOO_SHORT))
		{
			messages.add("The password is too short.");
		}
		else if (errorSet.contains(StringValidatorError.TOO_LONG))
		{
			messages.add("The password is too long.");
		}

		return messages;
	}

	private List<String> getPasswordConfirmationErrorMessages(Set<StringValidatorError> errorSet)
	{
		List<String> messages = new ArrayList<>();

		if (errorSet.contains(StringValidatorError.NO_PATTERN_MATCH))
		{
			messages.add("The passwords aren't equal.");
		}

		return messages;
	}
}

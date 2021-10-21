package krazune.krps.user.pagecontrollers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import krazune.krps.user.User;
import krazune.krps.user.UserDAO;
import krazune.krps.util.ConnectionFactory;
import krazune.krps.util.PropertiesLoader;
import krazune.krps.util.validators.StringValidator;
import krazune.krps.util.validators.StringValidatorError;

public class RegistrationPageController extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
	}
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
				response.sendRedirect("/registration");

				return;
			}

			int argon2SaltSize = propertiesLoader.getArgon2SaltSize();
			int argon2HashSize = propertiesLoader.getArgon2HashSize();
			int argon2Iterators = propertiesLoader.getArgon2Iterations();
			int argon2Memory = propertiesLoader.getArgon2Memory();
			int argon2Parallelism = propertiesLoader.getArgon2Parallelism();

			String passwordHash = UserDAO.getPasswordHash(password, argon2SaltSize, argon2HashSize, argon2Iterators, argon2Memory, argon2Parallelism);

			User newUser = new User(username, passwordHash);

			userDao.insert(newUser);

			HttpSession session = request.getSession(true);

			session.setAttribute("sessionUser", newUser);

			response.sendRedirect("/");
		}
		catch (Exception e)
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

		if (errorSet.contains(StringValidatorError.TOO_SHORT))
		{
			messages.add("Username too short.");
		}
		else if (errorSet.contains(StringValidatorError.TOO_LONG))
		{
			messages.add("Username too long.");
		}

		if (errorSet.contains(StringValidatorError.NO_PATTERN_MATCH))
		{
			messages.add("Username has invalid characters.");
		}

		return messages;
	}

	private List<String> getPasswordErrorMessages(Set<StringValidatorError> errorSet)
	{
		List<String> messages = new ArrayList<>();

		if (errorSet.contains(StringValidatorError.TOO_SHORT))
		{
			messages.add("Password too short.");
		}
		else if (errorSet.contains(StringValidatorError.TOO_LONG))
		{
			messages.add("Password too long.");
		}

		return messages;
	}

	private List<String> getPasswordConfirmationErrorMessages(Set<StringValidatorError> errorSet)
	{
		List<String> messages = new ArrayList<>();

		if (errorSet.contains(StringValidatorError.NO_PATTERN_MATCH))
		{
			messages.add("Passwords aren't equal.");
		}

		return messages;
	}
}

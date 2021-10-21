package krazune.krps.util.pagecontrollers;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
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

public class SettingsPageController extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.getRequestDispatcher("/WEB-INF/jsp/settings.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String currentPassword = request.getParameter("current-password");
		Set<StringValidatorError> currentPasswordErrors = validateCurrentPasswordInput(currentPassword);

		String newPassword = request.getParameter("password");
		Set<StringValidatorError> newPasswordErrors = validateNewPasswordInput(newPassword);

		String newPasswordConfirmation = request.getParameter("password-confirmation");
		Set<StringValidatorError> newPasswordConfirmationErrors = validateNewPasswordConfirmationInput(newPasswordConfirmation, newPassword);

		if (!currentPasswordErrors.isEmpty() || !newPasswordErrors.isEmpty() || !newPasswordConfirmationErrors.isEmpty())
		{
			List<String> currentPasswordErrorMessages = getCurrentPasswordErrorMessages(currentPasswordErrors);
			List<String> newPasswordErrorMessages = getNewPasswordErrorMessages(newPasswordErrors);
			List<String> newPasswordConfirmationErrorMessages = getNewPasswordConfirmationErrorMessages(newPasswordConfirmationErrors);

			request.setAttribute("currentPasswordErrorMessages", currentPasswordErrorMessages);
			request.setAttribute("newPasswordErrorMessages", newPasswordErrorMessages);
			request.setAttribute("newPasswordConfirmationErrorMessages", newPasswordConfirmationErrorMessages);

			request.getRequestDispatcher("/WEB-INF/jsp/settings.jsp").forward(request, response);

			return;
		}

		HttpSession session = request.getSession(true);
		User sessionUser = (User)session.getAttribute("sessionUser");

		try
		{
			PropertiesLoader propertiesLoader = (PropertiesLoader)request.getAttribute("propertiesLoader");
			String jdbcUrl = propertiesLoader.getJdbcUrl();
			String jdbcUsername = propertiesLoader.getJdbcUser();
			String jdbcPassword = propertiesLoader.getJdbcPassword();

			ConnectionFactory connectionFactory = new ConnectionFactory(jdbcUrl, jdbcUsername, jdbcPassword);

			Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
			char[] currentPasswordArray = currentPassword.toCharArray();
			boolean correctPassword = argon2.verify(sessionUser.getPasswordHash(), currentPasswordArray);

			argon2.wipeArray(currentPasswordArray);

			if (!correctPassword)
			{
				request.setAttribute("accountErrorMessage", "Invalid password.");
				request.getRequestDispatcher("/WEB-INF/jsp/settings.jsp").forward(request, response);

				return;
			}

			int argon2SaltSize = propertiesLoader.getArgon2SaltSize();
			int argon2HashSize = propertiesLoader.getArgon2HashSize();
			int argon2Iterators = propertiesLoader.getArgon2Iterations();
			int argon2Memory = propertiesLoader.getArgon2Memory();
			int argon2Parallelism = propertiesLoader.getArgon2Parallelism();

			String newPasswordHash = UserDAO.getPasswordHash(newPassword, argon2SaltSize, argon2HashSize, argon2Iterators, argon2Memory, argon2Parallelism);

			sessionUser.setPasswordHash(newPasswordHash);

			UserDAO userDao = new UserDAO(connectionFactory);

			userDao.update(sessionUser);

			response.sendRedirect("/");
		}
		catch (Exception e)
		{
			throw new ServletException(e);
		}
	}

	private Set<StringValidatorError> validateCurrentPasswordInput(String password)
	{
		StringValidator passwordValidator = new StringValidator();

		passwordValidator.setInput(password);

		passwordValidator.setMinimumSize(1);
		passwordValidator.setMaximumSize(128);

		passwordValidator.validate();

		return passwordValidator.getErrors();
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

		passwordConfirmationValidator.setMinimumSize(6);
		passwordConfirmationValidator.setMaximumSize(128);
		passwordConfirmationValidator.setPattern("\\Q" + password + "\\E"); // This is not efficient, but it's not critical.

		passwordConfirmationValidator.validate();

		return passwordConfirmationValidator.getErrors();
	}

	private List<String> getCurrentPasswordErrorMessages(Set<StringValidatorError> errorSet)
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

	private List<String> getNewPasswordErrorMessages(Set<StringValidatorError> errorSet)
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

	private List<String> getNewPasswordConfirmationErrorMessages(Set<StringValidatorError> errorSet)
	{
		List<String> messages = new ArrayList<>();

		if (errorSet.contains(StringValidatorError.NO_PATTERN_MATCH))
		{
			messages.add("Passwords aren't equal.");
		}

		return messages;
	}
}

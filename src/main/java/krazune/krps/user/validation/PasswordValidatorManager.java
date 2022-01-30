package krazune.krps.user.validation;

import krazune.krps.validation.SimpleValidator;
import krazune.krps.validation.StringSizeValidation;
import krazune.krps.validation.StringSizeValidationError;
import krazune.krps.validation.ValidationError;
import krazune.krps.validation.ValidatorManager;

public class PasswordValidatorManager extends ValidatorManager<String>
{
	private static final String PASSWORD_TOO_SHORT_ERROR_MESSAGE = "The password is too short.";
	private static final String PASSWORD_TOO_LONG_ERROR_MESSAGE = "The password is too long.";

	public PasswordValidatorManager()
	{
		validator = new SimpleValidator<String>();

		validator.addValidation(new StringSizeValidation(6, 64));
	}

	@Override
	protected String getErrorMessage(ValidationError validationError)
	{
		if (validationError == StringSizeValidationError.STRING_TOO_SHORT)
		{
			return PASSWORD_TOO_SHORT_ERROR_MESSAGE;
		}

		if (validationError == StringSizeValidationError.STRING_TOO_LONG)
		{
			return PASSWORD_TOO_LONG_ERROR_MESSAGE;
		}

		return null;
	}
}

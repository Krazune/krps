package krazune.krps.user.validation;

import krazune.krps.validation.SimpleValidator;
import krazune.krps.validation.StringEqualityValidation;
import krazune.krps.validation.StringEqualityValidationError;
import krazune.krps.validation.ValidationError;
import krazune.krps.validation.ValidatorManager;

public class PasswordConfirmationValidatorManager extends ValidatorManager<String>
{
	private static final String PASSWORDS_NO_EQUAL_ERROR_MESSAGE = "The passwords are not equal.";

	public PasswordConfirmationValidatorManager(String expected)
	{
		validator = new SimpleValidator<String>();

		validator.addValidation(new StringEqualityValidation(expected));
	}

	@Override
	protected String getErrorMessage(ValidationError validationError)
	{
		if (validationError == StringEqualityValidationError.NOT_EQUALS)
		{
			return PASSWORDS_NO_EQUAL_ERROR_MESSAGE;
		}

		return null;
	}
}

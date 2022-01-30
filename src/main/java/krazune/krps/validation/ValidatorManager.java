package krazune.krps.validation;

import java.util.ArrayList;
import java.util.List;

public abstract class ValidatorManager<T>
{
	protected Validator<T> validator;

	public List<String> validate(T input) throws ValidationException
	{
		List<String> errorMessages = new ArrayList<>();
		List<ValidationError> errors = validator.validate(input);

		for (int i = 0; i < errors.size(); ++i)
		{
			errorMessages.add(getErrorMessage(errors.get(i)));
		}

		return errorMessages;
	}

	protected abstract String getErrorMessage(ValidationError validationError);
}

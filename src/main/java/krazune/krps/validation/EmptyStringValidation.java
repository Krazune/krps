package krazune.krps.validation;

import java.util.ArrayList;
import java.util.List;

public class EmptyStringValidation implements Validation<String>
{
	@Override
	public List<ValidationError> execute(String input)
	{
		List<ValidationError> errors = new ArrayList<>();

		if (input.isEmpty())
		{
			errors.add(EmptyStringValidationError.EMPTY_STRING);

			return errors;
		}

		return errors;
	}
}

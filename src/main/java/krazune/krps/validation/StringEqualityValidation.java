package krazune.krps.validation;

import java.util.ArrayList;
import java.util.List;

public class StringEqualityValidation implements Validation<String>
{
	private String expectedString;

	public StringEqualityValidation(String expectedString)
	{
		this.expectedString = expectedString;
	}

	@Override
	public List<ValidationError> execute(String input)
	{
		List<ValidationError> errors = new ArrayList<>();

		if (!input.equals(expectedString))
		{
			errors.add(StringEqualityValidationError.NOT_EQUALS);
		}

		return errors;
	}
}

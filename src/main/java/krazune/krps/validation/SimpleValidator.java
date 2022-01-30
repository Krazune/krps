package krazune.krps.validation;

import java.util.ArrayList;
import java.util.List;

public class SimpleValidator<T> implements Validator<T>
{
	private List<Validation<T>> validations = new ArrayList<>();

	@Override
	public Validator<T> addValidation(Validation newValidation)
	{
		validations.add(newValidation);

		return this;
	}

	@Override
	public List<ValidationError> validate(T input) throws ValidationException
	{
		List<ValidationError> errors = new ArrayList<>();

		for (int i = 0; i < validations.size(); ++i)
		{
			List<ValidationError> validationErrors = validations.get(i).execute(input);

			errors.addAll(validationErrors);
		}

		return errors;
	}
}

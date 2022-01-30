package krazune.krps.validation;

public class EmptyStringValidatorManager extends ValidatorManager<String>
{
	private static final String EMPTY_INPUT = "The %s cannot be empty.";

	private String identifier;

	public EmptyStringValidatorManager(String identifier)
	{
		this.identifier = identifier;
		validator = new SimpleValidator<String>();

		validator.addValidation(new EmptyStringValidation());
	}

	@Override
	protected String getErrorMessage(ValidationError validationError)
	{
		if (validationError == EmptyStringValidationError.EMPTY_STRING)
		{
			return String.format(EMPTY_INPUT, identifier);
		}

		return null;
	}
}

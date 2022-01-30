package krazune.krps.validation;

public enum EmptyStringValidationError implements ValidationError
{
	EMPTY_STRING("String is empty.");

	private final String description;

	EmptyStringValidationError(String description)
	{
		this.description = description;
	}

	@Override
	public String getDescription()
	{
		return description;
	}
}

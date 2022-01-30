package krazune.krps.validation;

public enum StringEqualityValidationError implements ValidationError
{
	NOT_EQUALS("String not equals to the expected string.");

	private final String description;

	StringEqualityValidationError(String description)
	{
		this.description = description;
	}

	@Override
	public String getDescription()
	{
		return description;
	}
}

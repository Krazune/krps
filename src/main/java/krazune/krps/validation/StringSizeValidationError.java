package krazune.krps.validation;

public enum StringSizeValidationError implements ValidationError
{
	STRING_TOO_LONG("String too long."),
	STRING_TOO_SHORT("String too short.");

	private final String description;

	StringSizeValidationError(String description)
	{
		this.description = description;
	}

	@Override
	public String getDescription()
	{
		return description;
	}
}

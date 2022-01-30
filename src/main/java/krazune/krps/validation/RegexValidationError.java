package krazune.krps.validation;

public enum RegexValidationError implements ValidationError
{
	FAILED_PATTERN_MATCH("String failed pattern match.");

	private final String description;

	RegexValidationError(String description)
	{
		this.description = description;
	}

	@Override
	public String getDescription()
	{
		return description;
	}
}

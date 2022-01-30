package krazune.krps.validation;

public enum StringRegexValidationError implements ValidationError
{
	FAILED_PATTERN_MATCH("String failed pattern match.");

	private final String description;

	StringRegexValidationError(String description)
	{
		this.description = description;
	}

	@Override
	public String getDescription()
	{
		return description;
	}
}

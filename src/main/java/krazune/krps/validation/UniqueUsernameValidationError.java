package krazune.krps.validation;

public enum UniqueUsernameValidationError implements ValidationError
{
	USERNAME_NOT_UNIQUE("Username not unique.");

	private final String description;

	UniqueUsernameValidationError(String description)
	{
		this.description = description;
	}

	@Override
	public String getDescription()
	{
		return description;
	}
}

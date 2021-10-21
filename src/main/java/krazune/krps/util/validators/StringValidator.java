package krazune.krps.util.validators;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class StringValidator
{
	private String input;

	private HashSet<StringValidatorError> errors = new HashSet<>();

	private int minimumSize = -1;
	private int maximumSize = -1;

	private String pattern = "";

	public void setInput(String input)
	{
		this.input = input;
	}

	public Set<StringValidatorError> getErrors()
	{
		return errors;
	}

	public int getMinimumSize()
	{
		return minimumSize;
	}

	public void setMinimumSize(int minimumSize)
	{
		this.minimumSize = minimumSize;
	}

	public int getMaximumSize()
	{
		return maximumSize;
	}

	public void setMaximumSize(int maximumSize)
	{
		this.maximumSize = maximumSize;
	}

	public String getPattern()
	{
		return pattern;
	}

	public void setPattern(String pattern)
	{
		if (pattern == null)
		{
			this.pattern = "";

			return;
		}

		this.pattern = pattern;
	}

	public void validate()
	{
		errors.clear();

		if (input == null)
		{
			errors.add(StringValidatorError.NULL);

			return;
		}

		if (input.length() < minimumSize)
		{
			errors.add(StringValidatorError.TOO_SHORT);

			return;
		}

		if (maximumSize > -1 && input.length() > maximumSize)
		{
			errors.add(StringValidatorError.TOO_LONG);

			return;
		}

		if (!pattern.isEmpty() && !Pattern.matches(pattern, input))
		{
			errors.add(StringValidatorError.NO_PATTERN_MATCH);
		}
	}

	public boolean isValid()
	{
		return errors.isEmpty();
	}

	public void clear()
	{
		errors.clear();

		minimumSize = -1;
		maximumSize = -1;

		pattern = "";
	}
}
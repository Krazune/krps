package krazune.krps.validation;

import java.util.ArrayList;
import java.util.List;

public class StringSizeValidation implements Validation<String>
{
	private int minSize = -1;
	private int maxSize = -1;

	public StringSizeValidation(int minSize, int maxSize)
	{
		this.minSize = minSize;
		this.maxSize = maxSize;
	}

	@Override
	public List<ValidationError> execute(String input)
	{
		List<ValidationError> errors = new ArrayList<>();

		if (!validMinSize(input))
		{
			errors.add(StringSizeValidationError.STRING_TOO_SHORT);

			return errors;
		}

		if (!validMaxSize(input))
		{
			errors.add(StringSizeValidationError.STRING_TOO_LONG);

			return errors;
		}

		return errors;
	}

	public int getMinSize()
	{
		return minSize;
	}

	public int getMaxSize()
	{
		return maxSize;
	}

	private boolean validMinSize(String input)
	{
		return minSize < 0 || input.length() >= minSize;
	}

	private boolean validMaxSize(String input)
	{
		return maxSize < 0 || input.length() <= maxSize;
	}
}

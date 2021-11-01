/*
	MIT License

	Copyright (c) 2021 Miguel Sousa

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.
*/
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
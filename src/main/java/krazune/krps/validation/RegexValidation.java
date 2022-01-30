package krazune.krps.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexValidation implements Validation<String>
{
	private String patternString;

	public RegexValidation(String patternString)
	{
		this.patternString = patternString;
	}

	@Override
	public List<ValidationError> execute(String input)
	{
		List<ValidationError> errors = new ArrayList<>();

		if (!validMatch(input))
		{
			errors.add(RegexValidationError.FAILED_PATTERN_MATCH);
		}

		return errors;
	}

	private boolean validMatch(String input)
	{
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(input);

		return matcher.find();
	}
}

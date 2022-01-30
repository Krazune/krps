package krazune.krps.validation;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringRegexValidationTest
{
	@Test
	public void minSizeTest()
	{
		StringRegexValidation validationAction = new StringRegexValidation("[a-z]+");
		List<ValidationError> errors = validationAction.execute("abcd");

		assertEquals(0, errors.size());

		errors = validationAction.execute("1234");

		assertEquals(1, errors.size());
		assertEquals(StringRegexValidationError.FAILED_PATTERN_MATCH, errors.get(0));
	}
}

package krazune.krps.validation;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegexValidationTest
{
	@Test
	public void minSizeTest()
	{
		RegexValidation validation = new RegexValidation("[a-z]+");
		List<ValidationError> errors = validation.execute("abcd");

		assertEquals(0, errors.size());

		errors = validation.execute("1234");

		assertEquals(1, errors.size());
		assertEquals(RegexValidationError.FAILED_PATTERN_MATCH, errors.get(0));
	}
}

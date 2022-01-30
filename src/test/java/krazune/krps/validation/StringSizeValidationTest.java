package krazune.krps.validation;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringSizeValidationTest
{
	@Test
	public void minSizeTest()
	{
		StringSizeValidation validationAction = new StringSizeValidation(3, -1);
		List<ValidationError> errors = validationAction.execute("123");

		assertEquals(0, errors.size());

		errors = validationAction.execute("12");

		assertEquals(1, errors.size());
		assertEquals(StringSizeValidationError.STRING_TOO_SHORT, errors.get(0));
	}

	@Test
	public void maxSizeTest()
	{
		StringSizeValidation validationAction = new StringSizeValidation(-1, 3);
		List<ValidationError> errors = validationAction.execute("123");

		assertEquals(0, errors.size());

		errors = validationAction.execute("1234");

		assertEquals(1, errors.size());
		assertEquals(StringSizeValidationError.STRING_TOO_LONG, errors.get(0));
	}
}

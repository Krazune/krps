package krazune.krps.validation;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringEqualityValidationTest
{
	@Test
	public void minSizeTest()
	{
		StringEqualityValidation validation = new StringEqualityValidation("expected");
		List<ValidationError> errors = validation.execute("expected");

		assertEquals(0, errors.size());

		errors = validation.execute("abc");

		assertEquals(1, errors.size());
		assertEquals(StringEqualityValidationError.NOT_EQUALS, errors.get(0));
	}
}

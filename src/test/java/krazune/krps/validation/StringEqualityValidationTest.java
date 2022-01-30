package krazune.krps.validation;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringEqualityValidationTest
{
	@Test
	public void minSizeTest()
	{
		StringEqualityValidation validationAction = new StringEqualityValidation("expected");
		List<ValidationError> errors = validationAction.execute("expected");

		assertEquals(0, errors.size());

		errors = validationAction.execute("abc");

		assertEquals(1, errors.size());
		assertEquals(StringEqualityValidationError.NOT_EQUALS, errors.get(0));
	}
}

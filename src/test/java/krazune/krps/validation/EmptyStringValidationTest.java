package krazune.krps.validation;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmptyStringValidationTest
{
	@Test
	public void emptyStringTest()
	{
		EmptyStringValidation validation = new EmptyStringValidation();
		List<ValidationError> errors = validation.execute("");

		assertEquals(1, errors.size());
		assertEquals(EmptyStringValidationError.EMPTY_STRING, errors.get(0));
	}

	@Test
	public void nonEmptyStringTest()
	{
		EmptyStringValidation validation = new EmptyStringValidation();
		List<ValidationError> errors = validation.execute("123");

		assertEquals(0, errors.size());
	}
}

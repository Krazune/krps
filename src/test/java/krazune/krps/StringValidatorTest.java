package krazune.krps;

import java.util.Set;
import krazune.krps.util.validators.StringValidator;
import krazune.krps.util.validators.StringValidatorError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringValidatorTest
{
	private StringValidator stringValidator;

	@BeforeEach
	public void init()
	{
		stringValidator = new StringValidator();
	}

	@Test
	public void nullErrorTest()
	{
		stringValidator.setInput(null);

		stringValidator.validate();

		assertFalse(stringValidator.isValid());

		Set<StringValidatorError> errors = stringValidator.getErrors();

		assertTrue(errors.contains(StringValidatorError.NULL));
		assertTrue(errors.size() == 1);
	}

	@Test
	public void minimumSizeErrorTest()
	{
		stringValidator.setMinimumSize(5);
		stringValidator.setInput("1234");

		stringValidator.validate();

		assertFalse(stringValidator.isValid());

		Set<StringValidatorError> errors = stringValidator.getErrors();

		assertTrue(errors.contains(StringValidatorError.TOO_SHORT));
		assertTrue(errors.size() == 1);
	}

	@Test
	public void minimumSizeNoErrorTest()
	{
		stringValidator.setMinimumSize(5);
		stringValidator.setInput("12345");

		stringValidator.validate();

		assertTrue(stringValidator.isValid());

		Set<StringValidatorError> errors = stringValidator.getErrors();

		assertTrue(errors.isEmpty());
	}

	@Test
	public void maximumSizeErrorTest()
	{
		stringValidator.setMaximumSize(5);
		stringValidator.setInput("123456");

		stringValidator.validate();

		assertFalse(stringValidator.isValid());

		Set<StringValidatorError> errors = stringValidator.getErrors();

		assertTrue(errors.contains(StringValidatorError.TOO_LONG));
		assertTrue(errors.size() == 1);
	}

	@Test
	public void maximumSizeNoErrorTest()
	{
		stringValidator.setMaximumSize(5);
		stringValidator.setInput("12345");

		stringValidator.validate();

		assertTrue(stringValidator.isValid());

		Set<StringValidatorError> errors = stringValidator.getErrors();

		assertTrue(errors.isEmpty());
	}

	@Test
	public void patternErrorTest()
	{
		stringValidator.setPattern("[a-z]{3}");
		stringValidator.setInput("123");

		stringValidator.validate();

		assertFalse(stringValidator.isValid());

		Set<StringValidatorError> errors = stringValidator.getErrors();

		assertTrue(errors.contains(StringValidatorError.NO_PATTERN_MATCH));
		assertTrue(errors.size() == 1);
	}

	@Test
	public void patternNoErrorTest()
	{
		stringValidator.setPattern("[a-z]{3}");
		stringValidator.setInput("abc");

		stringValidator.validate();

		assertTrue(stringValidator.isValid());

		Set<StringValidatorError> errors = stringValidator.getErrors();

		assertTrue(errors.isEmpty());
	}

	@Test
	public void clearTest()
	{
		stringValidator.setMinimumSize(1);
		stringValidator.setMaximumSize(2);

		stringValidator.clear();

		stringValidator.setInput("1234567890");

		stringValidator.validate();

		assertTrue(stringValidator.isValid());
	}
}
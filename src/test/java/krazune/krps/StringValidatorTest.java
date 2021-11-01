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
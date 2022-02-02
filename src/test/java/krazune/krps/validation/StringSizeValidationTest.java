/*
 * MIT License
 *
 * Copyright (c) 2022 Miguel Sousa
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package krazune.krps.validation;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringSizeValidationTest
{
	@Test
	public void minSizeTest()
	{
		StringSizeValidation validation = new StringSizeValidation(3, -1);
		List<ValidationError> errors = validation.execute("123");

		assertEquals(0, errors.size());

		errors = validation.execute("12");

		assertEquals(1, errors.size());
		assertEquals(StringSizeValidationError.STRING_TOO_SHORT, errors.get(0));
	}

	@Test
	public void maxSizeTest()
	{
		StringSizeValidation validation = new StringSizeValidation(-1, 3);
		List<ValidationError> errors = validation.execute("123");

		assertEquals(0, errors.size());

		errors = validation.execute("1234");

		assertEquals(1, errors.size());
		assertEquals(StringSizeValidationError.STRING_TOO_LONG, errors.get(0));
	}
}

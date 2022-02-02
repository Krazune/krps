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
package krazune.krps.user.validation;

import krazune.krps.validation.SimpleValidator;
import krazune.krps.validation.StringEqualityValidation;
import krazune.krps.validation.StringEqualityValidationError;
import krazune.krps.validation.ValidationError;
import krazune.krps.validation.ValidatorManager;

public class PasswordConfirmationValidatorManager extends ValidatorManager<String>
{
	private static final String PASSWORDS_NO_EQUAL_ERROR_MESSAGE = "The passwords are not equal.";

	public PasswordConfirmationValidatorManager(String expected)
	{
		validator = new SimpleValidator<String>();

		validator.addValidation(new StringEqualityValidation(expected));
	}

	@Override
	protected String getErrorMessage(ValidationError validationError)
	{
		if (validationError == StringEqualityValidationError.NOT_EQUALS)
		{
			return PASSWORDS_NO_EQUAL_ERROR_MESSAGE;
		}

		return null;
	}
}

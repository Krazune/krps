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
import krazune.krps.validation.StringSizeValidation;
import krazune.krps.validation.StringSizeValidationError;
import krazune.krps.validation.ValidationError;
import krazune.krps.validation.ValidatorManager;

public class PasswordValidatorManager extends ValidatorManager<String>
{
	private static final String PASSWORD_TOO_SHORT_ERROR_MESSAGE = "The password is too short.";
	private static final String PASSWORD_TOO_LONG_ERROR_MESSAGE = "The password is too long.";

	public PasswordValidatorManager()
	{
		validator = new SimpleValidator<String>();

		validator.addValidation(new StringSizeValidation(6, 64));
	}

	@Override
	protected String getErrorMessage(ValidationError validationError)
	{
		if (validationError == StringSizeValidationError.STRING_TOO_SHORT)
		{
			return PASSWORD_TOO_SHORT_ERROR_MESSAGE;
		}

		if (validationError == StringSizeValidationError.STRING_TOO_LONG)
		{
			return PASSWORD_TOO_LONG_ERROR_MESSAGE;
		}

		return null;
	}
}

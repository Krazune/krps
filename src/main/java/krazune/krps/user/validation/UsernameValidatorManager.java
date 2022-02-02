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

import krazune.krps.user.dao.UserDao;
import krazune.krps.validation.RegexValidation;
import krazune.krps.validation.RegexValidationError;
import krazune.krps.validation.SimpleValidator;
import krazune.krps.validation.StringSizeValidation;
import krazune.krps.validation.StringSizeValidationError;
import krazune.krps.validation.UniqueUsernameValidation;
import krazune.krps.validation.UniqueUsernameValidationError;
import krazune.krps.validation.ValidationError;
import krazune.krps.validation.ValidatorManager;

public class UsernameValidatorManager extends ValidatorManager<String>
{
	private static final String USERNAME_TOO_SHORT_ERROR_MESSAGE = "The username is too short.";
	private static final String USERNAME_TOO_LONG_ERROR_MESSAGE = "The username is too long.";
	private static final String INVALID_CHARACTERS_ERROR_MESSAGE = "The username has invalid characters.";
	private static final String USERNAME_NOT_UNIQUE = "The username is already being used.";

	public UsernameValidatorManager(UserDao userDao)
	{
		validator = new SimpleValidator<String>();

		validator.addValidation(new StringSizeValidation(6, 16));
		validator.addValidation(new RegexValidation("^[a-zA-Z0-9]*$"));
		validator.addValidation(new UniqueUsernameValidation(userDao));
	}

	@Override
	protected String getErrorMessage(ValidationError validationError)
	{
		if (validationError == StringSizeValidationError.STRING_TOO_SHORT)
		{
			return USERNAME_TOO_SHORT_ERROR_MESSAGE;
		}

		if (validationError == StringSizeValidationError.STRING_TOO_LONG)
		{
			return USERNAME_TOO_LONG_ERROR_MESSAGE;
		}

		if (validationError == RegexValidationError.FAILED_PATTERN_MATCH)
		{
			return INVALID_CHARACTERS_ERROR_MESSAGE;
		}

		if (validationError == UniqueUsernameValidationError.USERNAME_NOT_UNIQUE)
		{
			return USERNAME_NOT_UNIQUE;
		}

		return null;
	}
}

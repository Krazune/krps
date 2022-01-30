package krazune.krps.validation;

import java.util.ArrayList;
import java.util.List;
import krazune.krps.dao.DaoException;
import krazune.krps.user.User;
import krazune.krps.user.dao.UserDao;

public class UniqueUsernameValidation implements Validation<String>
{
	private UserDao userDao;

	public UniqueUsernameValidation(UserDao userDao)
	{
		this.userDao = userDao;
	}

	@Override
	public List<ValidationError> execute(String username) throws ValidationException
	{
		try
		{
			List<ValidationError> errors = new ArrayList<>();
			User user = userDao.get(username);

			if (user != null)
			{
				errors.add(UniqueUsernameValidationError.USERNAME_NOT_UNIQUE);
			}

			return errors;
		}
		catch (DaoException e)
		{
			throw new ValidationException(e);
		}
	}
}

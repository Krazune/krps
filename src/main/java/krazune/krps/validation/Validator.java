package krazune.krps.validation;

import java.util.List;

public interface Validator<T>
{
	Validator<T> addValidation(Validation<T> newValidation);
	List<ValidationError> validate(T input) throws ValidationException;
}

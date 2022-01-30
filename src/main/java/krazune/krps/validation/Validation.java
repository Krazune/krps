package krazune.krps.validation;

import java.util.List;

public interface Validation<T>
{
	List<ValidationError> execute(T input) throws ValidationException;
}

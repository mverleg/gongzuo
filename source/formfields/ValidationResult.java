package formfields;

import javax.annotation.Nonnull;

// todo: use a concrete E
// todo: allow multiple error messages
public final class ValidationResult<T, E> {

    private final boolean isSuccess;
    private final T cleanedResult;
    private final E validationError;

    private ValidationResult(T cleanedResult, E validationError) {
        assert (cleanedResult == null) != (validationError == null);
        isSuccess = validationError == null;
        this.cleanedResult = cleanedResult;
        this.validationError = validationError;
    }

    public static <T, E> ValidationResult<T, E> Success (@Nonnull T cleanedResult) {
        return new ValidationResult<T, E>(cleanedResult, null);
    }

    public static <T, E> ValidationResult<T, E> Failure (@Nonnull E validationError) {
        return new ValidationResult<T, E>(null, validationError);
    }

    public final boolean succesful() {
        return isSuccess;
    }

    public final T getResult() {
        assert isSuccess;
        return cleanedResult;
    }

    public final E getError() {
        assert !isSuccess;
        return validationError;
    }
}

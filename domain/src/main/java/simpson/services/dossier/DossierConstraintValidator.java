package simpson.services.dossier;

import jakarta.validation.Validation;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class DossierConstraintValidator {

    @SafeVarargs
    public static <T> void validate(final T... t) {
        try (var validatorFactory = Validation.buildDefaultValidatorFactory()) {
            var validator = validatorFactory.getValidator();
            var violations = Arrays.stream(t).flatMap(value -> validator.validate(value).stream()).collect(Collectors.toSet());
            if (!violations.isEmpty()) {
                throw new DossierConstraintViolationException(violations);
            }
        }
    }
}

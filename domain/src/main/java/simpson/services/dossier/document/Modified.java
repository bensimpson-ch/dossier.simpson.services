package simpson.services.dossier.document;

import ch.icyal.ddd.ValueObject;
import jakarta.validation.constraints.NotNull;
import simpson.services.dossier.DossierConstraintValidator;

import java.time.LocalDateTime;

@ValueObject
public record Modified(@NotNull LocalDateTime timestamp) {
    public Modified(final LocalDateTime timestamp) {
        this.timestamp = timestamp;
        DossierConstraintValidator.validate(this);
    }
}

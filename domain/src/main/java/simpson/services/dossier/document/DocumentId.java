package simpson.services.dossier.document;


import ch.icyal.ddd.ValueObject;
import jakarta.validation.constraints.NotNull;
import simpson.services.dossier.DossierConstraintValidator;

import java.util.UUID;

@ValueObject
public record DocumentId(@NotNull UUID value) {
    public DocumentId(final UUID value) {
        this.value = value;
        DossierConstraintValidator.validate(this);
    }

    public DocumentId() {
        this(UUID.randomUUID());
    }
}

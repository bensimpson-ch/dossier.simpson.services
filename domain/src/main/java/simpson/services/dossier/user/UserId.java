package simpson.services.dossier.user;

import jakarta.validation.constraints.NotNull;
import simpson.services.dossier.DossierConstraintValidator;

import java.util.UUID;
 
public record UserId(@NotNull UUID value) {
    public UserId(final UUID value) {
        this.value = value;
        DossierConstraintValidator.validate(this);
    }

    public UserId() {
        this(UUID.randomUUID());
    }
}

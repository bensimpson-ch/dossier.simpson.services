package simpson.services.dossier.document;

import ch.icyal.ddd.ValueObject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import simpson.services.dossier.DossierConstraintValidator;

@ValueObject
public record Name(@NotBlank @Size(min = 1, max = 128) String value) {

    public Name(final String value) {
        this.value = value;
        DossierConstraintValidator.validate(this);
    }
}

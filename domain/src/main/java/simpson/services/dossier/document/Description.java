package simpson.services.dossier.document;

import ch.icyal.ddd.ValueObject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import simpson.services.dossier.DossierConstraintValidator;

@ValueObject
public record Description(@NotBlank @Size(min = 1, max = 2048) String value) {
    public Description(final String value) {
        this.value = value;
        DossierConstraintValidator.validate(this);
    }
}

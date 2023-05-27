package simpson.services.dossier.document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import simpson.services.dossier.DossierConstraintValidator;

public record Keyword(@NotBlank @Size(min = 1, max = 128) String value) {

    public Keyword(final String value) {
        this.value = value;
        DossierConstraintValidator.validate(this);
    }
}
package simpson.services.dossier.document;

import ch.icyal.ddd.ValueObject;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import simpson.services.dossier.DossierConstraintValidator;

@ValueObject
public record Size(@Min(1) @Max(200000000) long value) {
    public Size(final long value) {
        this.value = value;
        DossierConstraintValidator.validate(this);
    }
}

package simpson.services.dossier.document;

import ch.icyal.ddd.ValueObject;
import jakarta.activation.MimeType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import simpson.services.dossier.DossierConstraintValidator;

@ValueObject
public record Content(@NotEmpty byte[] bytes, @NotNull MimeType mimeType) {
    public Content(final byte[] bytes, final MimeType mimeType) {
        this.bytes = bytes;
        this.mimeType = mimeType;
        DossierConstraintValidator.validate(this);
    }
}

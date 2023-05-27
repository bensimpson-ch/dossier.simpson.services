package simpson.services.dossier.document;

import ch.icyal.ddd.ValueObject;
import jakarta.validation.constraints.NotNull;
import simpson.services.dossier.DossierConstraintValidator;

@ValueObject
public record MetaData(@NotNull DocumentId documentId, @NotNull Name name, @NotNull Description description,
                       @NotNull Size size, @NotNull Modified modified) {
    public MetaData(final DocumentId documentId, final Name name, final Description description, final Size size, final Modified modified) {
        this.documentId = documentId;
        this.name = name;
        this.description = description;
        this.size = size;
        this.modified = modified;
        DossierConstraintValidator.validate(this);
    }
}

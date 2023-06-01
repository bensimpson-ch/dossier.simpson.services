package simpson.services.dossier.document;

import ch.icyal.ddd.ValueObject;
import jakarta.validation.constraints.NotNull;
import simpson.services.dossier.DossierConstraintValidator;

import java.util.List;

@ValueObject
public record MetaData(@NotNull DocumentId documentId, @NotNull Name name, @NotNull Description description,
                       @NotNull List<Keyword> keywords, @NotNull Size size, @NotNull Modified modified) {
    public MetaData(final DocumentId documentId, final Name name, final Description description, List<Keyword> keywords, final Size size, final Modified modified) {
        this.documentId = documentId;
        this.name = name;
        this.description = description;
        this.keywords = keywords;
        this.size = size;
        this.modified = modified;
        DossierConstraintValidator.validate(this);
    }
}

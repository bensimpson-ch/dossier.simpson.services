package simpson.services.dossier.document;

import ch.icyal.ddd.Aggregate;
import ch.icyal.ddd.AggregateId;
import jakarta.validation.constraints.NotNull;
import simpson.services.dossier.DossierConstraintValidator;

@Aggregate
public record Document(@AggregateId @NotNull DocumentId id, @NotNull Content content,
                       @NotNull MetaData metaData
) {
    public Document(final DocumentId id, final Content content, final MetaData metaData) {
        this.id = id;
        this.content = content;
        this.metaData = metaData;
        DossierConstraintValidator.validate(this);
    }
}

package simpson.services.dossier.document;

import ch.icyal.ddd.Aggregate;
import ch.icyal.ddd.AggregateId;
import jakarta.validation.constraints.NotNull;
import simpson.services.dossier.DossierConstraintValidator;

import java.util.List;

@Aggregate
public record Document(@AggregateId @NotNull DocumentId id, @NotNull Content content, @NotNull List<Keyword> keywords,
                       @NotNull MetaData metaData
) {
    public Document(final DocumentId id, final Content content, List<Keyword> keywords, final MetaData metaData) {
        this.id = id;
        this.content = content;
        this.metaData = metaData;
        this.keywords = keywords;
        DossierConstraintValidator.validate(this);
    }
}

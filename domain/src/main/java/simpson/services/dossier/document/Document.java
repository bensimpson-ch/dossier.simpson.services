package simpson.services.dossier.document;

import ch.icyal.ddd.Aggregate;
import ch.icyal.ddd.AggregateId;
import jakarta.validation.constraints.NotNull;
import simpson.services.dossier.DossierConstraintValidator;

@Aggregate
public record Document(@AggregateId @NotNull DocumentId id) {
    public Document(final DocumentId id) {
        this.id = id;
        DossierConstraintValidator.validate(this);
    }
}

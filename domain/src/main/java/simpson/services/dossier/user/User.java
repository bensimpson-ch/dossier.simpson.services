package simpson.services.dossier.user;

import ch.icyal.ddd.Aggregate;
import ch.icyal.ddd.AggregateId;
import jakarta.validation.constraints.NotNull;
import simpson.services.dossier.DossierConstraintValidator;

@Aggregate
public record User(@AggregateId @NotNull UserId id) {
    public User(final UserId id) {
        this.id = id;
        DossierConstraintValidator.validate(this);
    }
}

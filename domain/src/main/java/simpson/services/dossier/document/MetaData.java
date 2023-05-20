package simpson.services.dossier.document;

import ch.icyal.ddd.ValueObject;
import jakarta.validation.constraints.NotNull;
import simpson.services.dossier.DossierConstraintValidator;

@ValueObject
public record MetaData(@NotNull Name name, @NotNull Description description, @NotNull Size size,
                       @NotNull Modified modified) {
    public MetaData(final Name name, final Description description, final Size size, final Modified modified) {
        this.name = name;
        this.description = description;
        this.size = size;
        this.modified = modified;
        DossierConstraintValidator.validate(this);
    }
}

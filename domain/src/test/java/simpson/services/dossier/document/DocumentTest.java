package simpson.services.dossier.document;

import org.junit.jupiter.api.Test;
import simpson.services.dossier.DossierConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DocumentTest {

    @Test
    void testConstructor() {
        var document = new Document(new DocumentId());
        assertThat(document).isNotNull();
    }

    @Test
    void negativeTestConstructorValidations() {
        assertThatThrownBy(() -> {
            new Document(null);
        }).isInstanceOf(DossierConstraintViolationException.class);
    }
}

package simpson.services.domain;

import org.junit.jupiter.api.Test;
import simpson.services.dossier.DossierConstraintViolationException;
import simpson.services.dossier.document.Document;
import simpson.services.dossier.document.DocumentId;

import java.util.UUID;

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

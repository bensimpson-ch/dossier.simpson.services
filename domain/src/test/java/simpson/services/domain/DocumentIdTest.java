package simpson.services.domain;

import org.junit.jupiter.api.Test;
import simpson.services.dossier.DossierConstraintViolationException;
import simpson.services.dossier.document.Document;
import simpson.services.dossier.document.DocumentId;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DocumentIdTest {

    @Test
    void testEmptyConstructor() {
        var documentId = new DocumentId();
        assertThat(documentId.value()).isNotNull();
    }

    @Test
    void testConstructor() {
        var documentId = new DocumentId(UUID.randomUUID());
        assertThat(documentId.value()).isNotNull();
    }

    @Test
    void negativeTestConstructorValidations() {
        assertThatThrownBy(() -> {
            new DocumentId(null);
        }).isInstanceOf(DossierConstraintViolationException.class);
    }
}

package simpson.services.dossier.document;

import org.junit.jupiter.api.Test;
import simpson.services.dossier.DossierConstraintViolationException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ModifiedTest {

    @Test
    void testConstructor() {
        assertThatNoException().isThrownBy(() -> new Modified(LocalDateTime.now()));
    }

    @Test
    void negativeTestConstructorValidations() {
        assertThatThrownBy(() -> {
            new Modified(null);
        }).isInstanceOf(DossierConstraintViolationException.class);
    }
}

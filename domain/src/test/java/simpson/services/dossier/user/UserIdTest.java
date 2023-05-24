package simpson.services.dossier.user;

import org.junit.jupiter.api.Test;
import simpson.services.dossier.DossierConstraintViolationException;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserIdTest {

    @Test
    void testEmptyConstructor() {
        var userId = new UserId();
        assertThat(userId.value()).isNotNull();
    }

    @Test
    void testConstructor() {
        var userId = new UserId(UUID.randomUUID());
        assertThat(userId.value()).isNotNull();
    }

    @Test
    void negativeTestConstructorValidations() {
        assertThatThrownBy(() -> {
            new UserId(null);
        }).isInstanceOf(DossierConstraintViolationException.class);
    }
}

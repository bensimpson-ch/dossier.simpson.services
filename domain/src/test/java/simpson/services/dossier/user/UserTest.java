package simpson.services.dossier.user;

import org.junit.jupiter.api.Test;
import simpson.services.dossier.DossierConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static simpson.services.dossier.user.UserTestValues.ID;

class UserTest {

    @Test
    void testConstructor() {
        assertThatNoException().isThrownBy(() -> new User(ID));
    }

    @Test
    void negativeTestConstructorValidations() {
        assertThatThrownBy(() -> new User(null)).isInstanceOf(DossierConstraintViolationException.class);
    }

}

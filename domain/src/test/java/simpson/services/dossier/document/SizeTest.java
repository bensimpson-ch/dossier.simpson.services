package simpson.services.dossier.document;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import simpson.services.dossier.DossierConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SizeTest {

    @ParameterizedTest
    @ValueSource(longs = {1, 20000000})
    void testConstructor(long value) {
        assertThatNoException().isThrownBy(() -> new Size(value));
    }

    @ParameterizedTest
    @ValueSource(longs = {0, -1L, 200000001})
    void negativeTestConstructorValidations(long value) {
        assertThatThrownBy(() -> {
            new Size(value);
        }).isInstanceOf(DossierConstraintViolationException.class);
    }
}

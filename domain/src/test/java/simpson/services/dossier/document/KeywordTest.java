package simpson.services.dossier.document;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import simpson.services.dossier.DossierConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KeywordTest {

    static final String MAX_LENGTH_STRING = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678";

    @ParameterizedTest
    @ValueSource(strings = {"name", " a name ", MAX_LENGTH_STRING})
    void testConstructor(String value) {
        assertThatNoException().isThrownBy(() -> new Keyword(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", MAX_LENGTH_STRING + "?"})
    void negativeTestConstructorValidations(String value) {
        assertThatThrownBy(() -> {
            new Keyword(value);
        }).isInstanceOf(DossierConstraintViolationException.class);
    }
}

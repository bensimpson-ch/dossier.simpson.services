package simpson.services.dossier.document;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import simpson.services.dossier.DossierConstraintViolationException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static simpson.services.dossier.document.DocumentTestValues.*;

class MetaDataTest {

    private static Stream<Arguments> negativeTestConstructorArguments() {
        return Stream.of(
                Arguments.of(null, DESCRIPTION, SIZE, MODIFIED),
                Arguments.of(NAME, null, SIZE, MODIFIED),
                Arguments.of(NAME, DESCRIPTION, null, MODIFIED),
                Arguments.of(NAME, DESCRIPTION, SIZE, null)
        );
    }

    @Test
    void testConstructor() {
        assertThatNoException().isThrownBy(() -> new MetaData(NAME, DESCRIPTION, SIZE, MODIFIED));
    }

    @ParameterizedTest
    @MethodSource("negativeTestConstructorArguments")
    void negativeTestConstructorValidations(Name name, Description description, Size size, Modified modified) {
        assertThatThrownBy(() -> {
            new MetaData(name, description, size, modified);
        }).isInstanceOf(DossierConstraintViolationException.class);
    }

}

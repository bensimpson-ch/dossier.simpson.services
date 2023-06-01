package simpson.services.dossier.document;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import simpson.services.dossier.DossierConstraintViolationException;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static simpson.services.dossier.document.DocumentTestValues.*;

class MetaDataTest {

    private static Stream<Arguments> negativeTestConstructorArguments() {
        return Stream.of(
                Arguments.of(null, NAME, DESCRIPTION, KEYWORDS, SIZE, MODIFIED),
                Arguments.of(ID, null, DESCRIPTION, KEYWORDS, SIZE, MODIFIED),
                Arguments.of(ID, NAME, null, KEYWORDS, SIZE, MODIFIED),
                Arguments.of(ID, NAME, DESCRIPTION, null, SIZE, MODIFIED),
                Arguments.of(ID, NAME, DESCRIPTION, KEYWORDS, null, MODIFIED),
                Arguments.of(ID, NAME, DESCRIPTION, KEYWORDS, SIZE, null)
        );
    }

    @Test
    void testConstructor() {
        assertThatNoException().isThrownBy(() -> new MetaData(ID, NAME, DESCRIPTION, KEYWORDS, SIZE, MODIFIED));
    }

    @ParameterizedTest
    @MethodSource("negativeTestConstructorArguments")
    void negativeTestConstructorValidations(DocumentId documentId, Name name, Description description, List<Keyword> keywords, Size size, Modified modified) {
        assertThatThrownBy(() -> {
            new MetaData(documentId, name, description, keywords, size, modified);
        }).isInstanceOf(DossierConstraintViolationException.class);
    }

}

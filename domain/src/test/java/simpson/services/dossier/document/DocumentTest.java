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

class DocumentTest {

    private static Stream<Arguments> negativeTestConstructorArguments() {
        return Stream.of(
                Arguments.of(null, CONTENT, METADATA),
                Arguments.of(ID, null, METADATA),
                Arguments.of(ID, CONTENT, null)
        );
    }

    @ParameterizedTest
    @MethodSource("negativeTestConstructorArguments")
    void negativeTestConstructorValidations(DocumentId id, Content content, MetaData metaData) {
        assertThatThrownBy(() -> {
            new Document(id, content, metaData);
        }).isInstanceOf(DossierConstraintViolationException.class);
    }

    @Test
    void testConstructor() {
        assertThatNoException().isThrownBy(() -> new Document(ID, CONTENT, METADATA));
    }
}

package simpson.services.dossier.document;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import simpson.services.dossier.DossierConstraintViolationException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static simpson.services.dossier.document.DocumentTestValues.CONTENT;
import static simpson.services.dossier.document.DocumentTestValues.ID;

class DocumentTest {

    private static Stream<Arguments> negativeTestConstructorArguments() {
        return Stream.of(
                Arguments.of(null, CONTENT),
                Arguments.of(ID, null)
        );
    }

    @ParameterizedTest
    @MethodSource("negativeTestConstructorArguments")
    void negativeTestConstructorValidations(DocumentId id, Content content) {
        assertThatThrownBy(() -> {
            new Document(id, content);
        }).isInstanceOf(DossierConstraintViolationException.class);
    }

    @Test
    void testConstructor() {
        var document = new Document(ID, CONTENT);
        assertThat(document).isNotNull();
    }
}

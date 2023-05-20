package simpson.services.dossier.document;

import jakarta.activation.MimeType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import simpson.services.dossier.DossierConstraintViolationException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static simpson.services.dossier.document.DocumentTestValues.CONTENT_BYTES;
import static simpson.services.dossier.document.DocumentTestValues.CONTENT_MIME_TYPE;

class ContentTest {

    private static Stream<Arguments> negativeTestConstructorArguments() {
        return Stream.of(
                Arguments.of(null, CONTENT_MIME_TYPE),
                Arguments.of(new byte[0], CONTENT_MIME_TYPE),
                Arguments.of(CONTENT_BYTES, null)
        );
    }

    @Test
    void testConstructor() {
        var content = new Content(CONTENT_BYTES, CONTENT_MIME_TYPE);

        assertThat(content.bytes()).isNotEmpty();
        assertThat(content.mimeType()).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("negativeTestConstructorArguments")
    void negativeTestConstructorValidations(byte[] bytes, MimeType mimeType) {
        assertThatThrownBy(() -> {
            new Content(bytes, mimeType);
        }).isInstanceOf(DossierConstraintViolationException.class);
    }
}

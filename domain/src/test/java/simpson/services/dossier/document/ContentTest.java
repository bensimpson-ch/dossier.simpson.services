package simpson.services.dossier.document;

import jakarta.activation.MimeType;
import jakarta.activation.MimeTypeParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import simpson.services.dossier.DossierConstraintViolationException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ContentTest {

    @Test
    void testConstructor() throws MimeTypeParseException {
        var content = new Content("content".getBytes(), new MimeType("text/plain"));

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
    private static Stream<Arguments> negativeTestConstructorArguments() {
        return Stream.of(
                Arguments.of(null, new MimeType()),
                Arguments.of("".getBytes(), new MimeType()),
                Arguments.of("not empty".getBytes(), null)
        );
    }
}

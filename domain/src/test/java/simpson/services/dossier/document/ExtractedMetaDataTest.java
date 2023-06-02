package simpson.services.dossier.document;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import simpson.services.dossier.DossierConstraintViolationException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ExtractedMetaDataTest {
    private static Stream<Arguments> negativeTestConstructorArguments() {
        return Stream.of(
                Arguments.of(1, null, null, null, null, null, null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("negativeTestConstructorArguments")
    void negativeTestConstructorValidations(int pageCount, Map<String, String> customMetadata, String title, String author, String subject, String keywords,
                                            LocalDateTime creationDate, LocalDateTime modificationDate) {
        assertThatThrownBy(() -> {
            new ExtractedMetaData(pageCount, customMetadata, title, author, subject, keywords,
                    creationDate, modificationDate);
        }).isInstanceOf(DossierConstraintViolationException.class);
    }

    @Test
    void testConstructor() {
        assertThatNoException().isThrownBy(() -> new ExtractedMetaData(1, Collections.emptyMap(), null,
                null, null, null, null, null));
    }


}
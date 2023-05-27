package simpson.services.dossier.document;

import jakarta.activation.MimeType;
import jakarta.activation.MimeTypeParseException;

import java.time.LocalDateTime;

class DocumentTestValues {

    static final DocumentId ID = new DocumentId();
    static final byte[] CONTENT_BYTES = "test".getBytes();
    static final MimeType CONTENT_MIME_TYPE = createContentMimeType();
    static final Content CONTENT = new Content(CONTENT_BYTES, CONTENT_MIME_TYPE);
    static final Name NAME = new Name("name");
    static final Description DESCRIPTION = new Description("description");
    static final Size SIZE = new Size(12000);
    static final Modified MODIFIED = new Modified(LocalDateTime.now());
    static final MetaData METADATA = new MetaData(ID, NAME, DESCRIPTION, SIZE, MODIFIED);

    static MimeType createContentMimeType() {
        try {
            return new MimeType("text/plain");
        } catch (MimeTypeParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}

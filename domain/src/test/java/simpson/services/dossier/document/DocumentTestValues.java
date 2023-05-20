package simpson.services.dossier.document;

import jakarta.activation.MimeType;
import jakarta.activation.MimeTypeParseException;

class DocumentTestValues {

    static final DocumentId ID = new DocumentId();
    static final byte[] CONTENT_BYTES = "test".getBytes();
    static final MimeType CONTENT_MIME_TYPE = createContentMimeType();
    static final Content CONTENT = new Content(CONTENT_BYTES, CONTENT_MIME_TYPE);

    static MimeType createContentMimeType() {
        try {
            return new MimeType("text/plain");
        } catch (MimeTypeParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}

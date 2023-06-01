package simpson.services.dossier.jsf.pages.dossier.mapper;

import jakarta.activation.MimeType;
import jakarta.activation.MimeTypeParseException;
import org.primefaces.model.file.UploadedFile;
import simpson.services.dossier.document.*;

import java.time.LocalDateTime;
import java.util.List;

public enum UploadedFilesMapper {
    SINGLETON;

    public Content mapContent(UploadedFile uploadedFile) {
        return new Content(uploadedFile.getContent(), mimeType(uploadedFile.getContentType()));
    }

    public MetaData mapMetaData(UploadedFile uploadedFile) {
        var documentId = new DocumentId();
        return new MetaData(documentId, new Name(uploadedFile.getFileName()), new Description(uploadedFile.getFileName()), List.of(), new Size(uploadedFile.getSize()), new Modified(LocalDateTime.now()));
    }

    private MimeType mimeType(String contentType) {
        try {
            return new MimeType(contentType);
        } catch (MimeTypeParseException e) {
            throw new RuntimeException(e);
        }
    }
}

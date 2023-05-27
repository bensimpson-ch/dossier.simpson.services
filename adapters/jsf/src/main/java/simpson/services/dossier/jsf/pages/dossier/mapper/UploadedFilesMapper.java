package simpson.services.dossier.jsf.pages.dossier.mapper;

import jakarta.activation.MimeType;
import jakarta.activation.MimeTypeParseException;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;
import simpson.services.dossier.document.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public enum UploadedFilesMapper {
    SINGLETON;

    public Set<Document> map(UploadedFiles uploadedFiles) {
        return uploadedFiles.getFiles().stream().map(this::map).collect(Collectors.toSet());
    }

    public Document map(UploadedFile uploadedFile) {
        var documentId = new DocumentId();
        var content = new Content(uploadedFile.getContent(), mimeType(uploadedFile.getContentType()));
        var metadata = new MetaData(documentId, new Name(uploadedFile.getFileName()), new Description(uploadedFile.getFileName()), new Size(uploadedFile.getSize()), new Modified(LocalDateTime.now()));
        return new Document(documentId, content, metadata);
    }

    private MimeType mimeType(String contentType) {
        try {
            return new MimeType(contentType);
        } catch (MimeTypeParseException e) {
            throw new RuntimeException(e);
        }
    }
}

package simpson.services.dossier.document;

import jakarta.validation.constraints.NotNull;
import simpson.services.dossier.DossierConstraintValidator;

import java.time.LocalDateTime;
import java.util.Map;

public record ExtractedMetaData(int pageCount,
                                @NotNull Map<String, String> customMetadata,
                                String title, String author, String subject, String keywords,
                                LocalDateTime creationDate, LocalDateTime modificationDate) {

    public ExtractedMetaData(int pageCount, Map<String, String> customMetadata, String title, String author, String subject, String keywords,
                             LocalDateTime creationDate, LocalDateTime modificationDate) {
        this.pageCount = pageCount;
        this.customMetadata = customMetadata;
        this.title = title;
        this.author = author;
        this.subject = subject;
        this.keywords = keywords;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        DossierConstraintValidator.validate(this);
    }
}

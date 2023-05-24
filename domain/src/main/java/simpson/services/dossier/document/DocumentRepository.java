package simpson.services.dossier.document;

import ch.icyal.ddd.Repository;
import simpson.services.dossier.user.UserId;

import java.util.List;

@Repository
public interface DocumentRepository {
    void createDocument(Document document, UserId author);

    Document readDocument(DocumentId documentId, UserId reader);

    void updateDocument(Document document, UserId editor);

    void deleteDocument(DocumentId documentId, UserId editor);

    List<MetaData> queryDocumentMetaData(UserId reader);
}

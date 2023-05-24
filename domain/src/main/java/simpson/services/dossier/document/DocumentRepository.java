package simpson.services.dossier.document;

import ch.icyal.ddd.Repository;

import java.util.List;

@Repository
public interface DocumentRepository {
    void createDocument(Document document);

    List<MetaData> queryDocumentMetaData();

    void updateDocument(Document document);

    void deleteDocument(DocumentId documentId);

    Document readDocument(DocumentId documentId);
}

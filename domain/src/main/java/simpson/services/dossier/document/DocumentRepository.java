package simpson.services.dossier.document;

import ch.icyal.ddd.Repository;

import java.util.List;

@Repository
public interface DocumentRepository {
    void insertDocument(Document document);

    List<MetaData> queryDocumentMetaData();

    void updateDocument(Document document);

    Document readDocument(DocumentId documentId);
}

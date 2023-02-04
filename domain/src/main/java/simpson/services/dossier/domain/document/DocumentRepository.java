/**
 * Copyright 2023 Ben Simpson: ben@bensimpson.ch
 */
package simpson.services.dossier.domain.document;

import java.util.List;

public interface DocumentRepository {

    /**
     * Searches for documents available based upon the provided query
     *
     * @param documentRepositoryQuery Parameters for searching documents
     * @return a list of available Document objects
     */
    List<Document> queryDocuments(DocumentRepositoryQuery documentRepositoryQuery);

    void saveDocument(Document document);

}

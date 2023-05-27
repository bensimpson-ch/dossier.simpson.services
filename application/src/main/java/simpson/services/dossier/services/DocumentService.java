package simpson.services.dossier.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import simpson.services.dossier.document.Document;
import simpson.services.dossier.document.DocumentId;
import simpson.services.dossier.document.DocumentRepository;
import simpson.services.dossier.document.MetaData;
import simpson.services.dossier.user.UserId;

import java.util.List;

@Stateless
public class DocumentService {

    @Inject
    private UserId userId;

    @Inject
    private DocumentRepository documentRepository;

    public List<MetaData> getDocumentMetaData() {
        return documentRepository.queryDocumentMetaData(userId);
    }

    public void createDocument(Document document) {
        documentRepository.createDocument(document, userId);

    }

    public void saveDocument(Document document) {
        documentRepository.updateDocument(document, userId);
    }

    public void removeDocument(DocumentId documentId) {
        documentRepository.deleteDocument(documentId, userId);
    }

    public Document readDocument(DocumentId documentId) {
        return documentRepository.readDocument(documentId, userId);
    }

}

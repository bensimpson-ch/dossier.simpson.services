package simpson.services.dossier.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import simpson.services.dossier.document.Document;
import simpson.services.dossier.document.DocumentId;
import simpson.services.dossier.document.DocumentRepository;
import simpson.services.dossier.document.MetaData;

import java.util.List;

@RequestScoped
public class DocumentService {

    @Inject
    private DocumentRepository documentRepository;

    public List<MetaData> getDocumentMetaData() {
        return documentRepository.queryDocumentMetaData();
    }

    public void createDocument(Document document) {
        documentRepository.createDocument(document);
    }

    public void saveDocument(Document document) {
        documentRepository.updateDocument(document);
    }

    public void removeDocument(DocumentId documentId) {
        documentRepository.deleteDocument(documentId);
    }

    public Document readDocument(DocumentId documentId) {
        return documentRepository.readDocument(documentId);
    }

}

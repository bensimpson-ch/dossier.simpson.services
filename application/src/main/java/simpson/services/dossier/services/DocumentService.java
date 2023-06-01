package simpson.services.dossier.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import simpson.services.dossier.document.*;
import simpson.services.dossier.document.pdf.PdfReader;
import simpson.services.dossier.user.UserId;

import java.util.List;

@Stateless
public class DocumentService {

    @Inject
    private PdfReader pdfReader;

    @Inject
    private UserId userId;

    @Inject
    private DocumentRepository documentRepository;

    public List<MetaData> getDocumentMetaData() {
        return documentRepository.queryDocumentMetaData(userId);
    }

    public void createDocument(Content content, MetaData metaData) {
        var keywords = pdfReader.keywords(content);
        var document = new Document(metaData.documentId(), content, new MetaData(metaData.documentId(), metaData.name(), metaData.description(), keywords, metaData.size(), metaData.modified()));
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

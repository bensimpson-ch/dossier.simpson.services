package simpson.services.dossier.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import simpson.services.dossier.document.Document;
import simpson.services.dossier.document.DocumentId;
import simpson.services.dossier.document.DocumentRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DocumentServiceTest {

    @Mock
    private DocumentRepository documentRepository;

    @InjectMocks
    private DocumentService documentService;

    @Test
    void getDocumentMetaData() {
        documentService.getDocumentMetaData();

        verify(documentRepository).queryDocumentMetaData();
    }

    @Test
    void createDocument() {
        var document = mock(Document.class);

        documentService.createDocument(document);

        verify(documentRepository).createDocument(document);
    }

    @Test
    void saveDocument() {
        var document = mock(Document.class);

        documentService.saveDocument(document);

        verify(documentRepository).updateDocument(document);
    }

    @Test
    void readDocument() {
        var documentId = new DocumentId();

        documentService.readDocument(documentId);

        verify(documentRepository).readDocument(documentId);
    }
}
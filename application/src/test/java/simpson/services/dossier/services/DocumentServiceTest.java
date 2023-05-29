package simpson.services.dossier.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import simpson.services.dossier.document.Document;
import simpson.services.dossier.document.DocumentId;
import simpson.services.dossier.document.DocumentRepository;
import simpson.services.dossier.document.Keyword;
import simpson.services.dossier.document.pdf.PdfReader;
import simpson.services.dossier.user.UserId;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DocumentServiceTest {

    @Mock
    private PdfReader pdfReader;

    @Mock
    private UserId userId;

    @Mock
    private DocumentRepository documentRepository;

    @InjectMocks
    private DocumentService documentService;

    @Test
    void getDocumentMetaData() {
        documentService.getDocumentMetaData();

        verify(documentRepository).queryDocumentMetaData(userId);
    }

    @Test
    void createDocument() {
        var document = mock(Document.class);
        var keywords = List.of(new Keyword("test"));
        when(pdfReader.keywords(document)).thenReturn(keywords);

        documentService.createDocument(document);

        verify(documentRepository).createDocument(document, userId);
        verify(pdfReader).keywords(document);
        verify(documentRepository).replaceKeywords(document, keywords);
    }

    @Test
    void saveDocument() {
        var document = mock(Document.class);

        documentService.saveDocument(document);

        verify(documentRepository).updateDocument(document, userId);
    }

    @Test
    void removeDocument() {
        var documentId = new DocumentId();

        documentService.removeDocument(documentId);

        verify(documentRepository).deleteDocument(documentId, userId);
    }

    @Test
    void readDocument() {
        var documentId = new DocumentId();

        documentService.readDocument(documentId);

        verify(documentRepository).readDocument(documentId, userId);
    }
}
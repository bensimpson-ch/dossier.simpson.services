package simpson.services.dossier.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import simpson.services.dossier.document.*;
import simpson.services.dossier.document.pdf.PdfReader;
import simpson.services.dossier.user.UserId;

import java.time.LocalDateTime;
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
        var documentId = new DocumentId();
        var keywords = List.of(new Keyword("keyword"));
        var metaData = new MetaData(documentId, new Name("name"), new Description("description"), keywords, new Size(10), new Modified(LocalDateTime.now()));
        var content = mock(Content.class);
        when(pdfReader.keywords(content)).thenReturn(keywords);
        var document = new Document(documentId, content, metaData);

        documentService.createDocument(content, metaData);

        verify(pdfReader).keywords(content);
        verify(documentRepository).createDocument(document, userId);
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
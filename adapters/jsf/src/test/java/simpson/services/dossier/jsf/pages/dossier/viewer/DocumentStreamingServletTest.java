package simpson.services.dossier.jsf.pages.dossier.viewer;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import simpson.services.dossier.document.Document;
import simpson.services.dossier.services.DocumentService;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static simpson.services.dossier.jsf.pages.dossier.viewer.DocumentTestValues.*;

@ExtendWith(MockitoExtension.class)
class DocumentStreamingServletTest {

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private DocumentService documentService;

    @InjectMocks
    private DocumentStreamingServlet documentStreamingServlet;

    @Test
    void doGet() throws IOException {
        var document = new Document(ID, CONTENT, METADATA);
        var outputStream = Mockito.mock(ServletOutputStream.class);

        when(httpServletRequest.getParameter("documentId")).thenReturn(document.id().value().toString());
        when(documentService.readDocument(document.id())).thenReturn(document);
        when(httpServletResponse.getOutputStream()).thenReturn(outputStream);

        documentStreamingServlet.doGet(httpServletRequest, httpServletResponse);

        verify(documentService).readDocument(document.id());
    }

    @Test
    void doGetWithoutDocumentId() throws IOException {
        when(httpServletRequest.getParameter("documentId")).thenReturn("");
        
        documentStreamingServlet.doGet(httpServletRequest, httpServletResponse);

        verify(documentService, times(0)).readDocument(any());
    }
}
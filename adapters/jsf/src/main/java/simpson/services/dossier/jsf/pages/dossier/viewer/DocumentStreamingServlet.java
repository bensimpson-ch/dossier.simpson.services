package simpson.services.dossier.jsf.pages.dossier.viewer;

import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import simpson.services.dossier.document.DocumentId;
import simpson.services.dossier.services.DocumentService;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.UUID;


@WebServlet(name = "documentStreamingServlet", urlPatterns = "/viewer/document")
public class DocumentStreamingServlet extends HttpServlet {

    @EJB
    private DocumentService documentService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var documentIdParameter = request.getParameter("documentId");
        if (documentIdParameter != null && !documentIdParameter.isBlank()) {
            var documentId = new DocumentId(UUID.fromString(documentIdParameter));
            var document = documentService.readDocument(documentId);

            response.setContentType(document.content().mimeType().toString());

            var outputStream = response.getOutputStream();
            var bufferedOutputStream = new BufferedOutputStream(outputStream);

            bufferedOutputStream.write(document.content().bytes());
            bufferedOutputStream.close();
            outputStream.close();
        }
    }
}

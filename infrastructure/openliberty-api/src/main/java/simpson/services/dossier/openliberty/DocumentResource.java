package simpson.services.dossier.openliberty;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Path;
import simpson.services.dossier.generated.rest.api.DocumentApi;
import simpson.services.dossier.generated.rest.model.Document;
import simpson.services.dossier.generated.rest.model.DocumentReference;
import simpson.services.dossier.generated.rest.model.DocumentUpload;

import java.util.List;
import java.util.UUID;

@RequestScoped
@Path("/document")
public class DocumentResource implements DocumentApi {

    @Override
    public List<DocumentReference> listDocuments() {
        return List.of();
    }

    @Override
    public Document readDocument(UUID documentId) {
        return null;
    }

    @Override
    public DocumentReference uploadDocument(DocumentUpload documentUpload) {
        return null;
    }
}

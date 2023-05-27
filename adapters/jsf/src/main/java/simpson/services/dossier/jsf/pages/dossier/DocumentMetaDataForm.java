package simpson.services.dossier.jsf.pages.dossier;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import simpson.services.dossier.document.*;
import simpson.services.dossier.services.DocumentService;

import java.io.Serializable;
import java.time.LocalDateTime;

@Named
@ViewScoped
public class DocumentMetaDataForm implements Serializable {


    private static final long serialVersionUID = -4673173986383109013L;

    @EJB
    private DocumentService documentService;

    private MetaData metadata = null;
    private String name;
    private String description;

    @PostConstruct
    public void init() {
        this.metadata = (MetaData) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedDocument");
        this.name = metadata.name().value();
        this.description = metadata.description().value();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void handleFormSubmission() {
        var document = documentService.readDocument(metadata.documentId());
        var updatedMetadata = new MetaData(metadata.documentId(), new Name(name), new Description(description), metadata.size(), new Modified(LocalDateTime.now()));
        var updatedDocument = new Document(document.id(), document.content(), updatedMetadata);
        documentService.saveDocument(updatedDocument);
    }
}

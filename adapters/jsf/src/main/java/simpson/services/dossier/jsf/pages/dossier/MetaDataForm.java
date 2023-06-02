package simpson.services.dossier.jsf.pages.dossier;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import simpson.services.dossier.document.*;
import simpson.services.dossier.services.DocumentService;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Named
@RequestScoped
public class MetaDataForm implements Serializable {


    @Serial
    private static final long serialVersionUID = -4673173986383109013L;

    @EJB
    private DocumentService documentService;

    private Document document;
    private String name;
    private String description;

    private List<String> keywords;
    private ExtractedMetaData extractedMetaData;

    @PostConstruct
    public void init() {
        var metaData = (MetaData) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedDocument");
        this.document = this.documentService.readDocument(metaData.documentId());
        this.name = this.document.metaData().name().value();
        this.description = this.document.metaData().description().value();
        this.keywords = this.document.metaData().keywords().stream().map(Keyword::value).toList();
        this.extractedMetaData = documentService.extractMetaData(document.content());
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

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public ExtractedMetaData getExtractedMetaData() {
        return this.extractedMetaData;
    }

    public void formActionListener() {
        var updatedMetadata = new MetaData(document.id(), new Name(name), new Description(description), keywords.stream().map(Keyword::new).toList(), document.metaData().size(), new Modified(LocalDateTime.now()));
        var updatedDocument = new Document(document.id(), document.content(), updatedMetadata);
        documentService.saveDocument(updatedDocument);
    }
}

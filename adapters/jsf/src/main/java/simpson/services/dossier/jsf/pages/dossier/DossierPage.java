package simpson.services.dossier.jsf.pages.dossier;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.util.LangUtils;
import simpson.services.dossier.document.MetaData;
import simpson.services.dossier.services.DocumentService;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Named
@ViewScoped
public class DossierPage implements Serializable {


    @Serial
    private static final long serialVersionUID = -8304943492739221871L;
    @EJB
    private DocumentService documentService;

    private List<MetaData> documentMetaData;

    private List<MetaData> filteredDocumentMetaData;

    @PostConstruct
    public void loadData() {
        this.documentMetaData = new ArrayList<>(documentService.getDocumentMetaData());
    }


    public List<MetaData> getDocumentMetaData() {
        return documentMetaData;
    }

    public List<MetaData> getFilteredDocumentMetaData() {
        return filteredDocumentMetaData;
    }

    public void setFilteredDocumentMetaData(List<MetaData> filteredDocumentMetaData) {
        this.filteredDocumentMetaData = filteredDocumentMetaData;
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        var filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isBlank(filterText)) {
            return true;
        }

        var metadata = (MetaData) value;
        return metadata.name().value().toLowerCase().contains(filterText)
                || metadata.description().value().toLowerCase().contains(filterText);
    }

    public void onRowSelect(SelectEvent<MetaData> event) {
        setSelectedDocument(event.getObject());
    }

    public void onRowUnselect(UnselectEvent<MetaData> event) {
        setSelectedDocument(null);
    }

    public void deleteAction() {
        var param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("documentId");
        var optionalMetaData = this.documentMetaData.stream()
                .filter(metaData -> metaData.documentId().value().toString().equals(param)).findAny();
        optionalMetaData.map(MetaData::documentId).ifPresent(this.documentService::removeDocument);
        optionalMetaData.ifPresent(this::clearSelectedDocument);
        optionalMetaData.ifPresent(this.documentMetaData::remove);

    }

    public String getStyleClass() {
        return hasSelectedDocument() ? "selected" : "";
    }

    public MetaData getSelectedDocument() {
        return (MetaData) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedDocument");
    }

    public void setSelectedDocument(MetaData selectedDocument) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedDocument", selectedDocument);
    }

    private void clearSelectedDocument(MetaData metaData) {
        var optionalSelectedDocument = Optional.ofNullable(getSelectedDocument());
        optionalSelectedDocument.filter(selectedDocument -> selectedDocument.equals(metaData)).ifPresent(d -> FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("selectedDocument"));
    }

    public boolean hasSelectedDocument() {
        return getSelectedDocument() != null;
    }
}

package simpson.services.dossier.jsf.pages.dossier;

import jakarta.ejb.EJB;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import simpson.services.dossier.document.MetaData;
import simpson.services.dossier.services.DocumentService;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class DossierPage implements Serializable {


    private static final long serialVersionUID = -8304943492739221871L;
    @EJB
    private DocumentService documentService;

    public List<MetaData> getDocumentMetaData() {
        return documentService.getDocumentMetaData();
    }

    public void onRowSelect(SelectEvent<MetaData> event) {
        setSelectedDocument(event.getObject());
    }

    public void onRowUnselect(UnselectEvent<MetaData> event) {
        setSelectedDocument(null);
    }

    public void deleteAction() {
        this.documentService.removeDocument(this.getSelectedDocument().documentId());
        this.setSelectedDocument(null);
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

    public boolean hasSelectedDocument() {
        return getSelectedDocument() != null;
    }
}

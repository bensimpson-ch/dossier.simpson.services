package simpson.services.dossier.jsf.pages.dossier;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.util.LangUtils;
import simpson.services.dossier.document.MetaData;
import simpson.services.dossier.jsf.pages.dossier.mapper.UploadedFilesMapper;
import simpson.services.dossier.services.DocumentService;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Named
@ViewScoped
public class DossierPage implements Serializable {


    @Serial
    private static final long serialVersionUID = -8304943492739221871L;

    private static final String SELECTED_DOCUMENT_KEY = "selectedDocument";
    @EJB
    private transient DocumentService documentService;

    private transient List<MetaData> documentMetaData;

    private transient List<MetaData> filteredDocumentMetaData;

    String getRequestParameter(String parameterName) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(parameterName);
    }

    Map<String, Object> getSessionMap() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    }

    @PostConstruct
    public void loadData() {
        this.documentMetaData = new ArrayList<>(this.documentService.getDocumentMetaData());
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
        if (hasSelectedDocument()) {
            setSelectedDocument(null);
        }
        setSelectedDocument(event.getObject());
    }

    public void onRowUnselect(UnselectEvent<MetaData> event) {
        setSelectedDocument(null);
    }

    public void deleteAction() {
        var param = getRequestParameter("documentId");
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
        return (MetaData) getSessionMap().get(SELECTED_DOCUMENT_KEY);
    }

    public void setSelectedDocument(MetaData selectedDocument) {
        getSessionMap().put(SELECTED_DOCUMENT_KEY, selectedDocument);
    }

    private void clearSelectedDocument(MetaData metaData) {
        var optionalSelectedDocument = Optional.ofNullable(getSelectedDocument());
        optionalSelectedDocument.filter(selectedDocument -> selectedDocument.equals(metaData)).ifPresent(d -> getSessionMap().remove(SELECTED_DOCUMENT_KEY));
    }

    public void handleFileUpload(FileUploadEvent event) {
        var file = event.getFile();
        var content = UploadedFilesMapper.SINGLETON.mapContent(file);
        var metaData = UploadedFilesMapper.SINGLETON.mapMetaData(file);
        documentService.createDocument(content, metaData);
        loadData();
    }

    public boolean hasSelectedDocument() {
        return getSelectedDocument() != null;
    }
}

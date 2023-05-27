package simpson.services.dossier.jsf.pages.dossier.upload;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFiles;
import simpson.services.dossier.document.Document;
import simpson.services.dossier.jsf.pages.dossier.mapper.UploadedFilesMapper;
import simpson.services.dossier.services.DocumentService;

@Named
@RequestScoped
public class DossierUploadBean {

    @Inject
    private DocumentService documentService;

    private UploadedFiles uploadedFiles;

    public UploadedFiles getUploadedFiles() {
        return uploadedFiles;
    }

    public void setUploadedFiles(UploadedFiles uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }

    public void handleFileUpload(FileUploadEvent event) {
        var file = event.getFile();
        var document = UploadedFilesMapper.SINGLETON.map(file);
        documentService.createDocument(document);
    }

    public String uploadFiles() {
        var documents = UploadedFilesMapper.SINGLETON.map(this.uploadedFiles);
        for (Document document : documents) {
            documentService.createDocument(document);
        }
        return "";
    }
}
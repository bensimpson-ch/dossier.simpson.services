package simpson.services.dossier.jsf.pages.dossier.upload;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.FileUploadEvent;
import simpson.services.dossier.jsf.pages.dossier.mapper.UploadedFilesMapper;
import simpson.services.dossier.services.DocumentService;

@Named
@RequestScoped
public class DossierUploadBean {

    @Inject
    private DocumentService documentService;

    public void handleFileUpload(FileUploadEvent event) {
        var file = event.getFile();
        var content = UploadedFilesMapper.SINGLETON.mapContent(file);
        var metaData = UploadedFilesMapper.SINGLETON.mapMetaData(file);
        documentService.createDocument(content, metaData);
    }
}
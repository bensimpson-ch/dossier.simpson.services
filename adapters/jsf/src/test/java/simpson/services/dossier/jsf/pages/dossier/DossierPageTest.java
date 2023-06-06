package simpson.services.dossier.jsf.pages.dossier;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import simpson.services.dossier.services.DocumentService;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DossierPageTest {

    @Mock
    private DocumentService documentService;

    @InjectMocks
    private DossierPage dossierPage;


    @Test
    void testLoadData() {
        dossierPage.loadData();

        verify(documentService).getDocumentMetaData();
    }

}
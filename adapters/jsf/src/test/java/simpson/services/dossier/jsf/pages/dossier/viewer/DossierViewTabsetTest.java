package simpson.services.dossier.jsf.pages.dossier.viewer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DossierViewTabsetTest {


    @Test
    void defaultValues() {
        var dossierViewTabset = new DossierViewTabset();

        assertThat(dossierViewTabset.getActiveIndex()).isZero();
        assertThat(dossierViewTabset.showViewer()).isTrue();
        assertThat(dossierViewTabset.showMetadata()).isFalse();
    }


    @Test
    void showViewerAction() {
        var dossierViewTabset = new DossierViewTabset();
        dossierViewTabset.showViewerAction();


        assertThat(dossierViewTabset.getActiveIndex()).isZero();
        assertThat(dossierViewTabset.showViewer()).isTrue();
        assertThat(dossierViewTabset.showMetadata()).isFalse();
    }

    @Test
    void showMetadataAction() {
        var dossierViewTabset = new DossierViewTabset();
        dossierViewTabset.showMetadataAction();


        assertThat(dossierViewTabset.getActiveIndex()).isOne();
        assertThat(dossierViewTabset.showViewer()).isFalse();
        assertThat(dossierViewTabset.showMetadata()).isTrue();
    }

}
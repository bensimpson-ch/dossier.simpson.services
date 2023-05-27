package simpson.services.dossier.jsf.pages.dossier.viewer;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serial;
import java.io.Serializable;

@Named
@SessionScoped
public class DossierViewTabset implements Serializable {

    @Serial
    private static final long serialVersionUID = -3256556003637839053L;

    private boolean showViewer = true;
    private boolean showMetadata = false;

    private int activeIndex = 0;

    public int getActiveIndex() {
        return activeIndex;
    }

    public boolean showViewer() {
        return this.showViewer;
    }

    public boolean showMetadata() {
        return this.showMetadata;
    }

    public void showViewerAction() {
        this.showViewer = true;
        this.showMetadata = false;
        this.activeIndex = 0;
    }

    public void showMetadataAction() {
        this.showViewer = false;
        this.showMetadata = true;
        this.activeIndex = 1;
    }
}

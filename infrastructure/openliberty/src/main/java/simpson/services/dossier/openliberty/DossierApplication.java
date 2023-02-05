package simpson.services.dossier.openliberty;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.Set;

@ApplicationPath("/api")
public class DossierApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(DocumentResource.class);
    }
}

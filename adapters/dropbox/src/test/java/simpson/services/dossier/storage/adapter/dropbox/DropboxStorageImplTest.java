package simpson.services.dossier.storage.adapter.dropbox;

import org.junit.jupiter.api.Test;
import simpson.services.dossier.document.Document;

import java.util.HashSet;

class DropboxStorageImplTest {

    private final DropboxStorageImpl dropboxStorage = new DropboxStorageImpl();

    @Test
    void store() {
        var documents = new HashSet<Document>();

        dropboxStorage.store(documents);
    }
}
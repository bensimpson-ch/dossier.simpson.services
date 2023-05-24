package simpson.services.dossier.adapter.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import simpson.services.dossier.document.DocumentId;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DocumentRepositoryImplTest {

    @RegisterExtension
    private final TestDatabaseExtension databaseExtension = new TestDatabaseExtension();

    private EntityManager entityManager;
    private DocumentRepositoryImpl documentRepository;

    @BeforeEach
    void beforeEach() {
        this.entityManager = databaseExtension.entityManager();
        this.documentRepository = new DocumentRepositoryImpl(entityManager);
    }

    @Test
    void queryDocumentMetaDataThrowsException() {
        assertThatThrownBy(() -> documentRepository.queryDocumentMetaData()).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void insertDocumentThrowsException() {
        assertThatThrownBy(() -> documentRepository.insertDocument(null)).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void updateDocumentThrowsException() {
        assertThatThrownBy(() -> documentRepository.updateDocument(null)).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void testDocumentRead() {
        var documentId = new DocumentId();

        assertThatThrownBy(() -> documentRepository.readDocument(documentId)).isInstanceOf(EntityNotFoundException.class);
    }
}

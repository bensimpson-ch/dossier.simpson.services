package simpson.services.dossier.adapter.persistence;

import jakarta.activation.MimeType;
import jakarta.activation.MimeTypeParseException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import simpson.services.dossier.document.*;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DocumentRepositoryImplTest {

    @RegisterExtension
    private final TestDatabaseExtension databaseExtension = new TestDatabaseExtension();

    private EntityManager entityManager;
    private DocumentRepositoryImpl documentRepository;

    private static Document createTestDocument() throws MimeTypeParseException {
        var content = new Content("testcontent".getBytes(), new MimeType("plain/text"));
        var documentId = new DocumentId();
        var metaData = new MetaData(new Name("name"), new Description("description"), new Size(content.bytes().length), new Modified(LocalDateTime.now()));
        return new Document(documentId, content, metaData);
    }

    @BeforeEach
    void beforeEach() {
        this.entityManager = databaseExtension.entityManager();
        this.documentRepository = new DocumentRepositoryImpl(entityManager);
    }

    @Test
    void queryDocumentMetaData() throws MimeTypeParseException {
        this.entityManager.getTransaction().begin();
        var testDocument = createTestDocument();
        documentRepository.createDocument(testDocument);
        this.entityManager.getTransaction().commit();

        var metaDataList = documentRepository.queryDocumentMetaData();

        //THIS tests that userId query is false
        assertThat(metaDataList).doesNotContain(testDocument.metaData());
    }

    @Test
    void createDocumentThrowsPersistenceException() throws MimeTypeParseException {
        var document = createTestDocument();

        assertThatThrownBy(() -> {
            this.entityManager.getTransaction().begin();
            documentRepository.createDocument(document);
            documentRepository.createDocument(document);
            this.entityManager.getTransaction().commit();
        }).isInstanceOf(PersistenceException.class);

        assertThatThrownBy(() -> {
            documentRepository.readDocument(document.id());
        }).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void createDocument() throws MimeTypeParseException {
        var document = createTestDocument();

        this.entityManager.getTransaction().begin();
        documentRepository.createDocument(document);
        this.entityManager.getTransaction().commit();

        assertThat(documentRepository.readDocument(document.id())).isNotNull();
    }

    @Test
    void updateDocument() throws MimeTypeParseException {
        var document = createTestDocument();

        this.entityManager.getTransaction().begin();
        documentRepository.createDocument(document);
        this.entityManager.getTransaction().commit();

        assertThat(documentRepository.readDocument(document.id())).isNotNull();

        this.entityManager.getTransaction().begin();
        documentRepository.updateDocument(document);
        this.entityManager.getTransaction().commit();

        assertThat(documentRepository.readDocument(document.id()).metaData().modified().timestamp()).isAfter(document.metaData().modified().timestamp());
    }

    @Test
    void deleteDocument() throws MimeTypeParseException {
        var document = createTestDocument();

        this.entityManager.getTransaction().begin();
        documentRepository.createDocument(document);
        this.entityManager.getTransaction().commit();

        assertThat(documentRepository.readDocument(document.id())).isNotNull();

        this.entityManager.getTransaction().begin();
        documentRepository.deleteDocument(document.id());
        this.entityManager.getTransaction().commit();

        assertThatThrownBy(() -> documentRepository.readDocument(document.id())).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void testDocumentRead() {
        var documentId = new DocumentId();

        assertThatThrownBy(() -> documentRepository.readDocument(documentId)).isInstanceOf(EntityNotFoundException.class);
    }
}

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
import simpson.services.dossier.user.UserId;

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
        var reader = new UserId();

        this.entityManager.getTransaction().begin();
        var testDocument = createTestDocument();
        documentRepository.createDocument(testDocument, reader);
        this.entityManager.getTransaction().commit();

        var metaDataList = documentRepository.queryDocumentMetaData(reader);

        assertThat(metaDataList).usingRecursiveFieldByFieldElementComparatorIgnoringFields("modified").containsExactly(testDocument.metaData());
    }

    @Test
    void createDocumentThrowsPersistenceException() throws MimeTypeParseException {
        var author = new UserId();
        var document = createTestDocument();

        assertThatThrownBy(() -> {
            this.entityManager.getTransaction().begin();
            documentRepository.createDocument(document, author);
            documentRepository.createDocument(document, author);
            this.entityManager.getTransaction().commit();
        }).isInstanceOf(PersistenceException.class);

        assertThatThrownBy(() -> {
            documentRepository.readDocument(document.id(), author);
        }).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void createDocument() throws MimeTypeParseException {
        var author = new UserId();
        var document = createTestDocument();

        this.entityManager.getTransaction().begin();
        documentRepository.createDocument(document, author);
        this.entityManager.getTransaction().commit();

        assertThat(documentRepository.readDocument(document.id(), author)).isNotNull();
    }

    @Test
    void updateDocument() throws MimeTypeParseException {
        var editor = new UserId();
        var document = createTestDocument();

        this.entityManager.getTransaction().begin();
        documentRepository.createDocument(document, editor);
        this.entityManager.getTransaction().commit();

        assertThat(documentRepository.readDocument(document.id(), editor)).isNotNull();

        this.entityManager.getTransaction().begin();
        documentRepository.updateDocument(document, editor);
        this.entityManager.getTransaction().commit();

        assertThat(documentRepository.readDocument(document.id(), editor).metaData().modified().timestamp()).isAfter(document.metaData().modified().timestamp());
    }

    @Test
    void deleteDocument() throws MimeTypeParseException {
        var editor = new UserId();
        var document = createTestDocument();

        this.entityManager.getTransaction().begin();
        documentRepository.createDocument(document, editor);
        this.entityManager.getTransaction().commit();

        assertThat(documentRepository.readDocument(document.id(), editor)).isNotNull();

        this.entityManager.getTransaction().begin();
        documentRepository.deleteDocument(document.id(), editor);
        this.entityManager.getTransaction().commit();

        assertThatThrownBy(() -> documentRepository.readDocument(document.id(), editor)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void testDocumentRead() {
        var reader = new UserId();
        var documentId = new DocumentId();

        assertThatThrownBy(() -> documentRepository.readDocument(documentId, reader)).isInstanceOf(EntityNotFoundException.class);
    }
}

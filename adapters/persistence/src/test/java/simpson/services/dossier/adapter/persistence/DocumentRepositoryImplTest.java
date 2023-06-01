package simpson.services.dossier.adapter.persistence;

import jakarta.activation.MimeType;
import jakarta.activation.MimeTypeParseException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import simpson.services.dossier.document.*;
import simpson.services.dossier.user.UserId;

import java.time.LocalDateTime;
import java.util.List;

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
        List<Keyword> keywords = List.of(new Keyword("keyword"));
        var metaData = new MetaData(documentId, new Name("name"), new Description("description"), keywords, new Size(content.bytes().length), new Modified(LocalDateTime.now()));
        return new Document(documentId, content, metaData);
    }

    @BeforeEach
    void beforeEach() {
        this.entityManager = databaseExtension.entityManager();
        this.documentRepository = new DocumentRepositoryImpl();
        this.documentRepository.entityManager = this.entityManager;
    }

    @Test
    void queryDocumentMetaData() throws MimeTypeParseException {
        var reader = new UserId();
        var document = createTestDocument();

        this.databaseExtension.transaction(() -> documentRepository.createDocument(document, reader));

        var metaDataList = documentRepository.queryDocumentMetaData(reader);
        assertThat(metaDataList).usingRecursiveFieldByFieldElementComparatorIgnoringFields("modified").containsExactly(document.metaData());
    }

    @Test
    void createDocumentThrowsPersistenceException() throws MimeTypeParseException {
        var author = new UserId();
        var document = createTestDocument();

        assertThatThrownBy(() ->
                this.databaseExtension.transaction(() -> {
                            documentRepository.createDocument(document, author);
                            documentRepository.createDocument(document, author);
                        }
                )).isInstanceOf(PersistenceException.class);

        assertThatThrownBy(() -> documentRepository.readDocument(document.id(), author)).isInstanceOf(DocumentNotFoundException.class);
    }

    @Test
    void createDocument() throws MimeTypeParseException {
        var author = new UserId();
        var document = createTestDocument();

        this.databaseExtension.transaction(() -> documentRepository.createDocument(document, author));
        assertThat(documentRepository.readDocument(document.id(), author)).isNotNull();
    }

    @Test
    void readDocument() throws MimeTypeParseException {
        var author = new UserId();
        var document = createTestDocument();

        this.databaseExtension.transaction(() -> documentRepository.createDocument(document, author));
        assertThat(documentRepository.readDocument(document.id(), author)).isNotNull();
    }

    @Test
    void readDocumentThrowsEntityNotFoundException() throws MimeTypeParseException {
        var author = new UserId();
        var reader = new UserId();
        var document = createTestDocument();

        this.databaseExtension.transaction(() -> documentRepository.createDocument(document, author));
        assertThatThrownBy(() -> documentRepository.readDocument(document.id(), reader)).isInstanceOf(DocumentNotFoundException.class);
    }

    @Test
    void updateDocument() throws MimeTypeParseException {
        var editor = new UserId();
        var document = createTestDocument();

        this.databaseExtension.transaction(() -> documentRepository.createDocument(document, editor));
        assertThat(documentRepository.readDocument(document.id(), editor)).isNotNull();

        this.databaseExtension.transaction(() -> documentRepository.updateDocument(document, editor));
        assertThat(documentRepository.readDocument(document.id(), editor).metaData().modified().timestamp()).isAfter(document.metaData().modified().timestamp());
    }

    @Test
    void deleteDocument() throws MimeTypeParseException {
        var editor = new UserId();
        var document = createTestDocument();

        this.databaseExtension.transaction(() -> documentRepository.createDocument(document, editor));
        assertThat(documentRepository.readDocument(document.id(), editor)).isNotNull();

        this.databaseExtension.transaction(() -> documentRepository.deleteDocument(document.id(), editor));
        assertThatThrownBy(() -> documentRepository.readDocument(document.id(), editor)).isInstanceOf(DocumentNotFoundException.class);
    }

    @Test
    void testDocumentRead() {
        var reader = new UserId();
        var documentId = new DocumentId();

        assertThatThrownBy(() -> documentRepository.readDocument(documentId, reader)).isInstanceOf(DocumentNotFoundException.class);
    }
}

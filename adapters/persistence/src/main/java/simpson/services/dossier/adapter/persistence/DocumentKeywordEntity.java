package simpson.services.dossier.adapter.persistence;

import jakarta.persistence.*;

import java.util.UUID;

import static simpson.services.dossier.adapter.persistence.DocumentKeywordEntity.DELETE_ALL_BY_DOCUMENT_ID;

@Table(name = "DOCUMENT_KEYWORD")
@Entity
@NamedQuery(name = DELETE_ALL_BY_DOCUMENT_ID, query = "delete from DocumentKeywordEntity where documentId = :documentId")
public class DocumentKeywordEntity {

    static final String DELETE_ALL_BY_DOCUMENT_ID = "DELETE_ALL_BY_DOCUMENT_ID";
    @Id
    private UUID id;

    @Column(name = "DOCUMENT_ID", nullable = false)
    private UUID documentId;


    @Column(name = "KEYWORD", nullable = false)
    private String keyword;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getDocumentId() {
        return documentId;
    }

    public void setDocumentId(UUID documentId) {
        this.documentId = documentId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}

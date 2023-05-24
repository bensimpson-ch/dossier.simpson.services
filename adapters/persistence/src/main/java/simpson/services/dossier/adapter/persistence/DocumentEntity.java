package simpson.services.dossier.adapter.persistence;

import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "DOCUMENT")
@Entity
public class DocumentEntity {

    @Id
    private UUID id;

    @Lob
    @Column(name = "CONTENT", nullable = false)
    private byte[] content;

    @OneToOne(cascade = CascadeType.ALL)
    private DocumentMetaDataEntity documentMetaDataEntity;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public DocumentMetaDataEntity getDocumentMetaDataEntity() {
        return documentMetaDataEntity;
    }

    public void setDocumentMetaDataEntity(DocumentMetaDataEntity documentMetaDataEntity) {
        this.documentMetaDataEntity = documentMetaDataEntity;
    }
}

package simpson.services.dossier.adapter.persistence;

import jakarta.activation.MimeType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static simpson.services.dossier.adapter.persistence.DocumentMetaDataEntity.ALL_DOCUMENT_METADATA_BY_DOSSIER_USER;

@Entity
@Table(name = "DOCUMENT_METADATA")
@NamedQuery(name = ALL_DOCUMENT_METADATA_BY_DOSSIER_USER, query = "select dm from DocumentMetaDataEntity dm, " +
        "DocumentPermissionsEntity dp where dp.documentId = dm.id and dp.userId = :userId")
public class DocumentMetaDataEntity {

    static final String DOSSIER_USER = "dossierUserId";

    static final String ALL_DOCUMENT_METADATA_BY_DOSSIER_USER = "ALL_DOCUMENT_METADATA_BY_DOSSIER_USER";
    @Id
    private UUID id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "MIME_TYPE")
    private MimeType mimeType;

    @Column(name = "SIZE")
    private long size;

    @Column(name = "CREATED", columnDefinition = "TIMESTAMP", updatable = false)
    private LocalDateTime created;

    @Column(name = "MODIFIED", columnDefinition = "TIMESTAMP", insertable = false)
    private LocalDateTime modified;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "DOCUMENTID")
    private Set<DocumentPermissionsEntity> documentPermissions;


    public Set<DocumentPermissionsEntity> getDocumentPermissions() {
        return documentPermissions;
    }

    public void setDocumentPermissions(Set<DocumentPermissionsEntity> documentPermissions) {
        this.documentPermissions = documentPermissions;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MimeType getMimeType() {
        return mimeType;
    }

    public void setMimeType(MimeType mimeType) {
        this.mimeType = mimeType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}


package simpson.services.dossier.adapter.persistence;

import jakarta.persistence.*;
import simpson.services.dossier.document.Permission;

import java.util.UUID;

@Entity
@Table(name = "DOCUMENT_PERMISSION")
public class DocumentPermissionEntity {

    @Id
    private UUID id;

    @Column(name = "DOCUMENT_ID")
    private UUID documentId;

    @Column(name = "USER_ID")
    private UUID userId;

    @Column(name = "PERMISSION")
    @Enumerated(value = EnumType.STRING)
    private Permission permission;

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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}


package simpson.services.dossier.adapter.persistence;

import simpson.services.dossier.document.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

enum DocumentEntityMapper {

    SINGLETON;

    Document map(DocumentEntity documentEntity) {
        var documentMetaDataEntity = documentEntity.getDocumentMetaDataEntity();
        var modified = documentMetaDataEntity.getModified() == null ? new Modified(documentMetaDataEntity.getCreated()) : new Modified(documentMetaDataEntity.getModified());
        var metaData = new MetaData(new Name(documentMetaDataEntity.getName()), new Description(documentMetaDataEntity.getDescription()), new Size(documentMetaDataEntity.getSize()), modified);
        var content = new Content(documentEntity.getContent(), documentMetaDataEntity.getMimeType());
        return new Document(new DocumentId(documentEntity.getId()), content, metaData);
    }

    DocumentEntity mapForCreate(Document document) {

        var documentPermissionsEntity = new DocumentPermissionsEntity();
        documentPermissionsEntity.setPermissionMask(1);
        documentPermissionsEntity.setDocumentId(document.id().value());
        documentPermissionsEntity.setId(UUID.randomUUID());
        documentPermissionsEntity.setUserId(UUID.randomUUID());

        var documentMetaDataEntity = new DocumentMetaDataEntity();
        documentMetaDataEntity.setCreated(LocalDateTime.now());
        documentMetaDataEntity.setName(document.metaData().name().value());
        documentMetaDataEntity.setDescription(document.metaData().description().value());
        documentMetaDataEntity.setSize(document.metaData().size().value());
        documentMetaDataEntity.setMimeType(document.content().mimeType());
        documentMetaDataEntity.setDocumentPermissions(Set.of(documentPermissionsEntity));

        var documentEntity = new DocumentEntity();
        documentEntity.setId(document.id().value());
        documentEntity.setContent(document.content().bytes());
        documentEntity.setDocumentMetaDataEntity(documentMetaDataEntity);

        return documentEntity;
    }
}

package simpson.services.dossier.adapter.persistence;

import simpson.services.dossier.document.*;
import simpson.services.dossier.user.UserId;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

enum DocumentEntityMapper {

    SINGLETON;

    Document map(DocumentEntity documentEntity) {
        var documentMetaDataEntity = documentEntity.getDocumentMetaDataEntity();
        var modified = documentMetaDataEntity.getModified() == null ? new Modified(documentMetaDataEntity.getCreated()) : new Modified(documentMetaDataEntity.getModified());
        var keywords = documentEntity.getDocumentKeywordEntities().stream().map(DocumentKeywordEntity::getKeyword).map(Keyword::new).toList();
        var metaData = new MetaData(new DocumentId(documentEntity.getId()), new Name(documentMetaDataEntity.getName()), new Description(documentMetaDataEntity.getDescription()), new Size(documentMetaDataEntity.getSize()), modified);
        var content = new Content(documentEntity.getContent(), documentMetaDataEntity.getMimeType());
        return new Document(new DocumentId(documentEntity.getId()), content, keywords, metaData);
    }

    DocumentEntity mapForUpdate(Document document, DocumentEntity documentEntity) {
        documentEntity.setId(document.id().value());
        documentEntity.setContent(document.content().bytes());

        var documentMetaDataEntity = documentEntity.getDocumentMetaDataEntity();
        documentMetaDataEntity.setModified(LocalDateTime.now());
        documentMetaDataEntity.setName(document.metaData().name().value());
        documentMetaDataEntity.setDescription(document.metaData().description().value());
        documentMetaDataEntity.setSize(document.metaData().size().value());
        documentMetaDataEntity.setMimeType(document.content().mimeType());

        return documentEntity;
    }

    DocumentEntity mapForInsert(Document document, UserId owner) {

        var documentMetaDataEntity = new DocumentMetaDataEntity();
        documentMetaDataEntity.setId(document.id().value());
        documentMetaDataEntity.setCreated(LocalDateTime.now());
        documentMetaDataEntity.setName(document.metaData().name().value());
        documentMetaDataEntity.setDescription(document.metaData().description().value());
        documentMetaDataEntity.setSize(document.metaData().size().value());
        documentMetaDataEntity.setMimeType(document.content().mimeType());

        var documentPermissionEntities = createDocumentPermissionsForOwner(document, owner);
        documentMetaDataEntity.setDocumentPermissions(documentPermissionEntities);

        var documentKeywordEntities = document.keywords().stream().map(keyword -> this.map(document, keyword)).toList();

        var documentEntity = new DocumentEntity();
        documentEntity.setId(document.id().value());
        documentEntity.setContent(document.content().bytes());
        documentEntity.setDocumentKeywordEntities(documentKeywordEntities);
        documentEntity.setDocumentMetaDataEntity(documentMetaDataEntity);

        return documentEntity;
    }

    private Set<DocumentPermissionEntity> createDocumentPermissionsForOwner(Document document, UserId owner) {
        return Stream.of(Permission.READ, Permission.MODIFY, Permission.DELETE)
                .map(p -> createDocumentPermissionEntity(document.id(), owner, p))
                .collect(Collectors.toSet());
    }

    private DocumentKeywordEntity map(Document document, Keyword keyword) {
        var documentKeywordEntity = new DocumentKeywordEntity();
        documentKeywordEntity.setKeyword(keyword.value());
        documentKeywordEntity.setDocumentId(document.id().value());
        documentKeywordEntity.setId(UUID.randomUUID());
        return documentKeywordEntity;
    }

    private DocumentPermissionEntity createDocumentPermissionEntity(DocumentId documentId, UserId userId, Permission permission) {
        var documentPermissionEntity = new DocumentPermissionEntity();
        documentPermissionEntity.setId(UUID.randomUUID());
        documentPermissionEntity.setPermission(permission);
        documentPermissionEntity.setUserId(userId.value());
        documentPermissionEntity.setDocumentId(documentId.value());
        return documentPermissionEntity;
    }
}

package simpson.services.dossier.adapter.persistence;

import simpson.services.dossier.document.Permission;
import simpson.services.dossier.user.UserId;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

enum DocumentPermissionEntityMapper {

    SINGLETON;

    Set<Permission> permissions(Optional<DocumentEntity> optionalDocumentEntity, UserId id) {
        return optionalDocumentEntity.stream().map(DocumentEntity::getDocumentMetaDataEntity)
                .map(DocumentMetaDataEntity::getDocumentPermissions)
                .flatMap(Set::stream)
                .filter(documentPermissionEntity -> documentPermissionEntity.getUserId().equals(id.value()))
                .map(DocumentPermissionEntity::getPermission)
                .collect(Collectors.toSet());
    }
}

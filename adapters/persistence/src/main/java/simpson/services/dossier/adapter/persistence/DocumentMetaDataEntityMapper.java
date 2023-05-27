package simpson.services.dossier.adapter.persistence;

import simpson.services.dossier.document.*;

enum DocumentMetaDataEntityMapper {

    SINGLETON;

    MetaData map(DocumentMetaDataEntity documentMetaDataEntity) {
        var modified = documentMetaDataEntity.getModified() != null ? documentMetaDataEntity.getModified() : documentMetaDataEntity.getCreated();
        return new MetaData(new DocumentId(documentMetaDataEntity.getId()), new Name(documentMetaDataEntity.getName()), new Description(documentMetaDataEntity.getDescription()), new Size(documentMetaDataEntity.getSize()), new Modified(modified));
    }
}

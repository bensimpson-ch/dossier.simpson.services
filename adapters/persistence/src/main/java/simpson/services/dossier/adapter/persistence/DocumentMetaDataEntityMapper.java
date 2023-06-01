package simpson.services.dossier.adapter.persistence;

import simpson.services.dossier.document.*;

enum DocumentMetaDataEntityMapper {

    SINGLETON;

    MetaData map(DocumentMetaDataEntity documentMetaDataEntity) {
        var modified = documentMetaDataEntity.getModified() != null ? documentMetaDataEntity.getModified() : documentMetaDataEntity.getCreated();
        var keywords = documentMetaDataEntity.getKeywords().stream().map(DocumentKeywordEntity::getKeyword).map(Keyword::new).toList();
        return new MetaData(new DocumentId(documentMetaDataEntity.getId()), new Name(documentMetaDataEntity.getName()), new Description(documentMetaDataEntity.getDescription()), keywords, new Size(documentMetaDataEntity.getSize()), new Modified(modified));
    }
}

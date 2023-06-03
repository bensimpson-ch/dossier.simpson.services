package simpson.services.dossier.adapter.persistence;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import simpson.services.dossier.document.*;
import simpson.services.dossier.user.UserId;

import java.util.List;
import java.util.Optional;


@Dependent
public class DocumentRepositoryImpl implements DocumentRepository {


    @PersistenceContext(unitName = "dossier")
    protected EntityManager entityManager;

    @Override
    public void createDocument(Document document, UserId author) {
        var documentEntity = DocumentEntityMapper.SINGLETON.mapForInsert(document, author);
        entityManager.persist(documentEntity);
        entityManager.flush();
    }


    @Override
    public Document readDocument(DocumentId documentId, UserId reader) {
        var optionalDocumentEntity = findDocumentEntity(documentId);

        if (optionalDocumentEntity.isEmpty() || missingPermission(optionalDocumentEntity, reader, Permission.READ)) {
            throw new DocumentNotFoundException("Document with id not found or not readable %s".formatted(documentId.value().toString()));
        } else {
            return optionalDocumentEntity.map(DocumentEntityMapper.SINGLETON::map).get();
        }


    }

    @Override
    public void updateDocument(Document document, UserId editor) {
        var optionalDocumentEntity = findDocumentEntity(document.id());

        if (optionalDocumentEntity.isEmpty() || missingPermission(optionalDocumentEntity, editor, Permission.MODIFY)) {
            throw new DocumentNotFoundException("Document with id not found or not readable %s".formatted(document.id().value().toString()));
        }

        var documentEntity = DocumentEntityMapper.SINGLETON.mapForUpdate(document, optionalDocumentEntity.get());
        entityManager.merge(documentEntity);
        entityManager.flush();
    }

    @Override
    public void deleteDocument(DocumentId documentId, UserId editor) {
        var optionalDocumentEntity = findDocumentEntity(documentId);

        if (optionalDocumentEntity.isEmpty() || missingPermission(optionalDocumentEntity, editor, Permission.DELETE)) {
            throw new DocumentNotFoundException("Document with id not found or not readable %s".formatted(documentId.value().toString()));
        }

        entityManager.remove(optionalDocumentEntity.get());
    }

    @Override
    public List<MetaData> queryDocumentMetaData(UserId userId) {
        return entityManager.createNamedQuery(DocumentMetaDataEntity.READ_DOCUMENT_METADATA_BY_USER, DocumentMetaDataEntity.class)
                .setParameter("userId", userId.value())
                .getResultStream().map(DocumentMetaDataEntityMapper.SINGLETON::map).toList();
    }

    private Optional<DocumentEntity> findDocumentEntity(DocumentId documentId) {
        return Optional.ofNullable(entityManager.find(DocumentEntity.class, documentId.value()));

    }

    private boolean missingPermission(Optional<DocumentEntity> documentEntity, UserId id, Permission permission) {
        return !DocumentPermissionEntityMapper.SINGLETON.permissions(documentEntity, id).contains(permission);
    }
}

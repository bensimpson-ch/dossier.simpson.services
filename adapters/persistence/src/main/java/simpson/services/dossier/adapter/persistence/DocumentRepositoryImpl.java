package simpson.services.dossier.adapter.persistence;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import simpson.services.dossier.document.Document;
import simpson.services.dossier.document.DocumentId;
import simpson.services.dossier.document.DocumentRepository;
import simpson.services.dossier.document.MetaData;
import simpson.services.dossier.user.UserId;

import java.util.List;
import java.util.Optional;

@Dependent
public class DocumentRepositoryImpl implements DocumentRepository {


    @PersistenceContext(unitName = "dossier")
    protected EntityManager entityManager;

    @Inject
    public DocumentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createDocument(Document document, UserId author) {
        var documentEntity = DocumentEntityMapper.SINGLETON.map(document, author);
        entityManager.persist(documentEntity);
    }

    @Override
    public Document readDocument(DocumentId documentId, UserId reader) {
        return findDocumentEntity(documentId).map(DocumentEntityMapper.SINGLETON::map).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void updateDocument(Document document, UserId editor) {
        var documentEntity = DocumentEntityMapper.SINGLETON.map(document, editor);
        entityManager.merge(documentEntity);
    }

    @Override
    public void deleteDocument(DocumentId documentId, UserId editor) {
        findDocumentEntity(documentId).ifPresent(documentEntity -> entityManager.remove(documentEntity));
    }

    private Optional<DocumentEntity> findDocumentEntity(DocumentId documentId) {
        return Optional.ofNullable(entityManager.find(DocumentEntity.class, documentId.value()));
    }

    @Override
    public List<MetaData> queryDocumentMetaData(UserId userId) {
        return entityManager.createNamedQuery(DocumentMetaDataEntity.ALL_DOCUMENT_METADATA_BY_DOSSIER_USER, DocumentMetaDataEntity.class)
                .setParameter("userId", userId.value())
                .getResultStream().map(DocumentMetaDataEntityMapper.SINGLETON::map).toList();
    }
}

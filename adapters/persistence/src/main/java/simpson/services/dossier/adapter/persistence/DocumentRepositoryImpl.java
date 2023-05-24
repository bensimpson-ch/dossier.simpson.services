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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class DocumentRepositoryImpl implements DocumentRepository {


    @PersistenceContext(unitName = "dossier")
    protected EntityManager entityManager;

    @Inject
    public DocumentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createDocument(Document document) {
        var documentEntity = DocumentEntityMapper.SINGLETON.map(document);
        entityManager.persist(documentEntity);
    }

    @Override
    public Document readDocument(DocumentId documentId) {
        return findDocumentEntity(documentId).map(DocumentEntityMapper.SINGLETON::map).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void updateDocument(Document document) {
        var documentEntity = DocumentEntityMapper.SINGLETON.map(document);
        entityManager.merge(documentEntity);
    }

    @Override
    public void deleteDocument(DocumentId documentId) {
        findDocumentEntity(documentId).ifPresent(documentEntity -> entityManager.remove(documentEntity));
    }

    private Optional<DocumentEntity> findDocumentEntity(DocumentId documentId) {
        return Optional.ofNullable(entityManager.find(DocumentEntity.class, documentId.value()));
    }

    @Override
    public List<MetaData> queryDocumentMetaData() {
        return entityManager.createNamedQuery(DocumentMetaDataEntity.ALL_DOCUMENT_METADATA_BY_DOSSIER_USER, DocumentMetaDataEntity.class)
                .setParameter("userId", UUID.randomUUID())
                .getResultStream().map(DocumentMetaDataEntityMapper.SINGLETON::map).toList();
    }
}

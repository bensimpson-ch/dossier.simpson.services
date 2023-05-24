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

@Dependent
public class DocumentRepositoryImpl implements DocumentRepository {


    @PersistenceContext(unitName = "dossier")
    protected EntityManager entityManager;

    @Inject
    public DocumentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<MetaData> queryDocumentMetaData() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public void insertDocument(Document document) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public void updateDocument(Document document) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public Document readDocument(DocumentId documentId) {
        var optionalDocumentEntity = Optional.ofNullable(entityManager.find(DocumentEntity.class, documentId.value()));
        return optionalDocumentEntity.map(DocumentEntityMapper.SINGLETON::map).orElseThrow(EntityNotFoundException::new);
    }
}

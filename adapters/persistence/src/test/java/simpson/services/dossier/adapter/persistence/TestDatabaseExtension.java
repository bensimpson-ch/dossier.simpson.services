package simpson.services.dossier.adapter.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Map;

public class TestDatabaseExtension implements AfterTestExecutionCallback {

    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) {
        entityManager.close();
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
            entityManagerFactory = null;
        }
    }

    public EntityManager entityManager() {
        var connectionProperties = Map.of("jakarta.persistence.jdbc.user", "sa", "jakarta.persistence.jdbc.password", "sa");
        this.entityManagerFactory = Persistence.createEntityManagerFactory("dossier-test", connectionProperties);
        this.entityManager = this.entityManagerFactory.createEntityManager();
        return this.entityManager;
    }

    public <T> void transaction(Runnable runnable) {
        this.entityManager.getTransaction().begin();
        runnable.run();
        this.entityManager.getTransaction().commit();
    }
}

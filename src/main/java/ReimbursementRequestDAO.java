import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.util.List;

public class ReimbursementRequestDAO implements ReimbursementRequestDAOInterface<ReimbursementRequest, String> {

    private Session currentSession;

    private Transaction currentTransaction;

    public ReimbursementRequestDAO() {
    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(ReimbursementRequest.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        System.out.println("Configured");
        return sessionFactory;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }


    public void persist(ReimbursementRequest entity) {
        getCurrentSession().save(entity);
    }

    public void update(ReimbursementRequest entity) {
        getCurrentSession().update(entity);
    }

    public ReimbursementRequest findById(String id) {
        ReimbursementRequest reimbursementRequest = (ReimbursementRequest) getCurrentSession().get(ReimbursementRequest.class, id);
        return reimbursementRequest;
    }

    public void delete(ReimbursementRequest entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<ReimbursementRequest> findAll() {
        List<ReimbursementRequest> requests = (List<ReimbursementRequest>) getCurrentSession().createQuery("from ReimbursementRequest").list();
        return requests;
    }

    public void deleteAll() {
        List<ReimbursementRequest> entityList = findAll();
        for (ReimbursementRequest entity : entityList) {
            delete(entity);
        }
    }
}

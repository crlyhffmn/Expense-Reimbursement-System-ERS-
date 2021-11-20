import java.util.List;

public class ReimbursementRequestDAOImpl {

    private static ReimbursementRequestDAO requestDAO;

    public ReimbursementRequestDAOImpl() {
        requestDAO = new ReimbursementRequestDAO();
    }

    public void persist(ReimbursementRequest entity) {
        requestDAO.openCurrentSessionwithTransaction();
        requestDAO.persist(entity);
        requestDAO.closeCurrentSessionwithTransaction();
    }

    public void update(ReimbursementRequest entity) {
        requestDAO.openCurrentSessionwithTransaction();
        requestDAO.update(entity);
        requestDAO.closeCurrentSessionwithTransaction();
    }

    public ReimbursementRequest findById(String id) {
        requestDAO.openCurrentSession();
        ReimbursementRequest reimbursementRequest = requestDAO.findById(id);
        requestDAO.closeCurrentSession();
        return reimbursementRequest;
    }

    public void delete(String id) {
        requestDAO.openCurrentSessionwithTransaction();
        ReimbursementRequest reimbursementRequest = requestDAO.findById(id);
        requestDAO.delete(reimbursementRequest);
        requestDAO.closeCurrentSessionwithTransaction();
    }

    public List<ReimbursementRequest> findAll() {
        requestDAO.openCurrentSession();
        List<ReimbursementRequest> requests = requestDAO.findAll();
        requestDAO.closeCurrentSession();
        return requests;
    }

    public void deleteAll() {
        requestDAO.openCurrentSessionwithTransaction();
        requestDAO.deleteAll();
        requestDAO.closeCurrentSessionwithTransaction();
    }

    public ReimbursementRequestDAO ReimbursementRequestDAO() {
        return requestDAO;
    }
}

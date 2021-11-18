import java.util.List;

public class EmployeeDAO implements EmployeeDAOInterface<Employee, String> {

    private SessionDAO sessionDAO;

    public EmployeeDAO() {
        sessionDAO = new SessionDAO();
    }

    public void persist(Employee entity) {
        sessionDAO.getCurrentSession().save(entity);
    }

    public void update(Employee entity) {
        sessionDAO.getCurrentSession().update(entity);
    }

    public Employee findById(String id) {
        Employee employee = (Employee) sessionDAO.getCurrentSession().get(Employee.class, id);
        return employee;
    }

    public void delete(Employee entity) {
        sessionDAO.getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Employee> findAll() {
        List<Employee> employees = (List<Employee>) sessionDAO.getCurrentSession().createQuery("from Employee").list();
        return employees;
    }

    public void deleteAll() {
        List<Employee> entityList = findAll();
        for (Employee entity : entityList) {
            delete(entity);
        }
    }
}

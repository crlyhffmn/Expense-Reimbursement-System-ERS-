import java.util.List;

public class EmployeeDAOImpl {

    private static EmployeeDAO employeeDAO;

    public EmployeeDAOImpl() {
        employeeDAO = new EmployeeDAO();
    }

    public void persist(Employee entity) {
        employeeDAO.openCurrentSessionwithTransaction();
        employeeDAO.persist(entity);
        employeeDAO.closeCurrentSessionwithTransaction();
    }

    public void update(Employee entity) {
        employeeDAO.openCurrentSessionwithTransaction();
        employeeDAO.update(entity);
        employeeDAO.closeCurrentSessionwithTransaction();
    }

    public Employee findById(String id) {
        employeeDAO.openCurrentSession();
        Employee employee = employeeDAO.findById(id);
        employeeDAO.closeCurrentSession();
        return employee;
    }

    public List<Employee> findByUsername(String username) {
        employeeDAO.openCurrentSession();
        List<Employee> employees = employeeDAO.findByUsername(username);
        employeeDAO.closeCurrentSession();
        return employees;
    }

    public void delete(String id) {
        employeeDAO.openCurrentSessionwithTransaction();
        Employee employee = employeeDAO.findById(id);
        employeeDAO.delete(employee);
        employeeDAO.closeCurrentSessionwithTransaction();
    }

    public List<Employee> findAll() {
        employeeDAO.openCurrentSession();
        List<Employee> employees = employeeDAO.findAll();
        employeeDAO.closeCurrentSession();
        return employees;
    }

    public void deleteAll() {
        employeeDAO.openCurrentSessionwithTransaction();
        employeeDAO.deleteAll();
        employeeDAO.closeCurrentSessionwithTransaction();
    }

    public EmployeeDAO employeeDAO() {
        return employeeDAO;
    }
}
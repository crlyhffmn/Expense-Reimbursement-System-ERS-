import java.io.Serializable;
import java.util.List;

public interface EmployeeDAOInterface<T, Id extends Serializable> {

    public void persist(T entity);

    public void update(T entity);

    public T findById(Id id);

    public List<T> findByUsername(String username);

    public void delete(T entity);

    public List<T> findAll();

    public void deleteAll();

    public void deleteById(Class<?> type, Serializable id);

}
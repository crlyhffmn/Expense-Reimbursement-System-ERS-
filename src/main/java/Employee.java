import javax.persistence.*;

@Entity
@Table(name="employee")
public class Employee {
    @Id
    @Column(name="employee_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int employee_id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="firstName")
    private String firstName;
    @Column(name="lastName")
    private String lastName;
    @Column(name="isManager")
    private Boolean isManager;

    public Employee(){}

    public Employee(int id, String username, String password, String firstName, String lastName, Boolean isManager) {
        this.employee_id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isManager = isManager;
    }

    public int getId() {
        return employee_id;
    }

    public void setId(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getManager() {
        return isManager;
    }

    public void setManager(Boolean manager) {
        isManager = manager;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "Employee ID = " + employee_id +
                ", Username = " + username +
                ", First Name = " + firstName +
                ", Last Name = " + lastName +
                ", Manager Status = "+ isManager +
                '}';
    }
}
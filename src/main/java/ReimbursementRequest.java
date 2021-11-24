import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name="reimbursement_request")
public class ReimbursementRequest {
    @Id
    @Column(name="request_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long request_id;
    @Column(name="description")
    private String description;
    @Column(name="amount")
    private double amount;
    @Column(name="approved")
    private boolean approved;
    @Column(name="approved_string")
    private String approved_string;

    @ManyToOne(targetEntity = Employee.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;

    public ReimbursementRequest() {

    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public long getRequestId() {
        return request_id;
    }

    public void setRequestId(long request_id) {
        this.request_id = request_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApproved_string() {
        return approved_string;
    }

    public void setApproved_string(String approved_string) {
        this.approved_string = approved_string;
    }

    @Override
    public String toString() {
        return "Request{" +
                "Request ID =" + request_id +
                "Employee ID =" + employee.getId() +
                ", Amount = $" + amount +
                ", Approved ='" + approved +
                '}';
    }
}

import javax.persistence.*;

@Entity
@Table(name="reimbursement-request")
public class ReimbursementRequest {
    @Id
    @Column(name="request_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int request_id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private int empID;
    @Column(name="description")
    private String description;
    @Column(name="amount")
    private double amount;
    @Column(name="approved")
    private boolean approved;

    public int getRequestId() {
        return request_id;
    }

    public void setRequestId(int request_id) {
        this.request_id = request_id;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
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

    @Override
    public String toString() {
        return "Request{" +
                "Request ID =" + request_id +
                "Employee ID =" + empID +
                ", Amount = $" + amount +
                ", Approved ='" + approved +
                '}';
    }
}

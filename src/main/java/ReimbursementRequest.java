import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reimbursement-request")
public class ReimbursementRequest {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="empID")
    private int empID;
    @Column(name="amount")
    private double amount;
    @Column(name="approved")
    private boolean approved;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Request{" +
                "Request ID =" + id +
                "Employee ID =" + empID +
                ", Amount = $" + amount +
                ", Approved ='" + approved +
                '}';
    }
}

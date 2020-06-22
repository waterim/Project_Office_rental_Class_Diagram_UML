package s16603.mas.Project_s16603_11c_Office_rental.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name="invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long invoiceId;

    @NotBlank
    private double total;

    @OneToOne
    private Office office;

    @OneToMany
    private Set<Payment> paymentSet = new HashSet<>();

    public Invoice(@NotBlank double total) {
        this.total = total;
    }

    public void addOffice(Office office){
        this.office.addInvoice(this);
        setOffice(office);
    }
    public void removeOffice(){
        if(this.office != null){
            this.office.removeInvoice(this);
            setOffice(null);
        }
        else{
            System.out.println("Office is empty");
        }
    }

    public void addPayment(Payment payment){
        paymentSet.add(payment);
        payment.addInvoice(this);
    }

    public void removePayment(Payment payment){
        if(paymentSet.size() != 0){
            paymentSet.remove(payment);
            payment.removeInvoice();
        }
    }
}

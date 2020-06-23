package s16603.mas.Project_s16603_11c_Office_rental.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name="payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;

    @NotBlank
    private String method;

    @NotNull
    private int value;

    @ManyToOne
    private Invoice invoice;


    public Payment(@NotNull String method, @NotBlank int value) {
        this.method = method;
        this.value = value;
    }

    public void addInvoice(Invoice invoice){
        setInvoice(invoice);
        invoice.addPayment(this);
    }
    public void removeInvoice(){
        if(this.invoice != null) {
            invoice.removePayment(this);
            setInvoice(null);
        }else{
            System.out.println("Invoice is empty");
        }
    }
}

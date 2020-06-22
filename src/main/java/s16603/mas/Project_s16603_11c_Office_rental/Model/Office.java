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
@Table(name="office")
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long officeId;

    @NotBlank
    private String name;

    @NotBlank
    private int price;

    @NotBlank
    private String description;

    @OneToOne
    private VirtualOffice virtualOffice;
    @OneToOne
    private OpenSpaceOffice openSpaceOffice;
    @OneToOne
    private HomeOffice homeOffice;

    @Enumerated(EnumType.STRING)
    private OfficeStatus officeStatus;

    @OneToMany
    private Set<Rental> rentalsSet=new HashSet<>();

    @OneToOne
    private Invoice invoice;

    public Office(@NotBlank String name, @NotBlank int price, @NotBlank String description,
                  int virtualVideoConversationSize, int openNumberOfSeats, int homeWorkingDaysPerWeek, OfficeType officeType, OfficeStatus officeStatus) {
        this.name = name;
        this.price = price;
        this.description = description;

        this.officeStatus = officeStatus;
        if(officeType == OfficeType.HOME){
            this.homeOffice = new HomeOffice(this, homeWorkingDaysPerWeek);
            this.virtualOffice = null;
            this.openSpaceOffice = null;
        }
        if(officeType == OfficeType.OPEN_SPACE){
            this.openSpaceOffice = new OpenSpaceOffice(this, openNumberOfSeats);
            this.virtualOffice = null;
            this.homeOffice = null;
        }
        if(officeType == OfficeType.VIRTUAL){
            this.virtualOffice = new VirtualOffice(this, virtualVideoConversationSize);
            this.homeOffice = null;
            this.openSpaceOffice = null;
        }
    }

    public void becomeHomeOffice(int homeWorkingDaysPerWeek) {
        if (!isHomeOffice()) {
            this.homeOffice = new HomeOffice(this, homeWorkingDaysPerWeek);
            this.openSpaceOffice = null;
            this.virtualOffice = null;
        }
    }

    public void becomeOpenOffice(int openNumberOfSeats) {
        if (!isOpenSpaceOffice()) {
            this.openSpaceOffice = new OpenSpaceOffice(this, openNumberOfSeats);
            this.homeOffice = null;
            this.virtualOffice = null;
        }
    }

    public void becomeVirtualOffice(int virtualVideoConversationSize) {
        if (!isVirtualOffice()) {
            this.virtualOffice = new VirtualOffice(this, virtualVideoConversationSize);
            this.homeOffice = null;
            this.openSpaceOffice = null;
        }
    }

    public boolean isHomeOffice() {
        if (this.homeOffice != null) {
            return true;
        }
        return false;
    }

    public boolean isVirtualOffice() {
        if (this.virtualOffice != null) {
            return true;
        }
        return false;
    }

    public boolean isOpenSpaceOffice() {
        if (this.openSpaceOffice != null) {
            return true;
        }
        return false;
    }

    public void addRental(Rental rental){
        if (rental == null) {
            throw new IllegalArgumentException("Rental cannot be null");
        }
        if (!this.rentalsSet.contains(rental)) {
            this.rentalsSet.add(rental);
            rental.setOffice(this);
        }
    }

    public void createRental(Rental rental, Customer customer){
        this.addRental(rental);
        customer.addRental(rental);
    }


    public void removeRental(Rental rental){
        if (rental == null) {
            throw new IllegalArgumentException("Rental cannot be null");
        }
        if (this.rentalsSet.contains(rental)) {
            if (this.rentalsSet.size() == 1 ) {
                throw new IllegalArgumentException("can not remove the last rental from " + getName());
            }
            this.rentalsSet.remove(rental);
            rental.removeRental();
        }
    }

    public void addInvoice(Invoice invoice){
        setInvoice(invoice);
        invoice.addOffice(this);
    }
    public void removeInvoice(Invoice invoice){
        setInvoice(null);
        invoice.removeOffice(this);
    }

}

package s16603.mas.Project_s16603_11c_Office_rental.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@ToString
@Table(name="office")
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long officeId;

    @NotBlank
    private String name;

    @NotNull
    private int price;

    @NotBlank
    private String description;

//    @OneToOne
//    private VirtualOffice virtualOffice;
//    @OneToOne
//    private OpenSpaceOffice openSpaceOffice;
//    @OneToOne
//    private HomeOffice homeOffice;

    private int virtualVideoConversationSize;
    private int openNumberOfSeats;
    private int homeWorkingDaysPerWeek;

    @Enumerated(EnumType.STRING)
    private OfficeStatus officeStatus;

    @OneToMany
    private Set<Rental> rentalsSet=new HashSet<>();

    @OneToOne
    private Invoice invoice;

    @ManyToMany
    private Set<Owner> owners = new HashSet<>();

    @ManyToMany
    private Set<Owner> managedByOwners = new HashSet<>();

    @ManyToOne
    private Location location;

    public Office(@NotBlank String name, @NotNull int price, @NotBlank String description,
                  int virtualVideoConversationSize, int openNumberOfSeats, int homeWorkingDaysPerWeek,
                  OfficeType officeType, OfficeStatus officeStatus, Location location) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.location = location;
        this.location.addOffice(this);

        this.officeStatus = officeStatus;
//        if(officeType == OfficeType.HOME){
//            this.homeOffice = new HomeOffice(this, homeWorkingDaysPerWeek);
//            this.virtualOffice = null;
//            this.openSpaceOffice = null;
//        }
//        if(officeType == OfficeType.OPEN_SPACE){
//            this.openSpaceOffice = new OpenSpaceOffice(this, openNumberOfSeats);
//            this.virtualOffice = null;
//            this.homeOffice = null;
//        }
//        if(officeType == OfficeType.VIRTUAL){
//            this.virtualOffice = new VirtualOffice(this, virtualVideoConversationSize);
//            this.homeOffice = null;
//            this.openSpaceOffice = null;
//        }
        if(officeType == OfficeType.HOME){
            this.homeWorkingDaysPerWeek = homeWorkingDaysPerWeek;
            this.virtualVideoConversationSize = 0;
            this.openNumberOfSeats = 0;
        }
        if(officeType == OfficeType.OPEN_SPACE){
            this.openNumberOfSeats = openNumberOfSeats;
            this.virtualVideoConversationSize = 0;
            this.homeWorkingDaysPerWeek = 0;
        }
        if(officeType == OfficeType.VIRTUAL){
            this.virtualVideoConversationSize = virtualVideoConversationSize;
            this.homeWorkingDaysPerWeek = 0;
            this.openNumberOfSeats = 0;
        }

    }

    public void becomeHomeOffice(int homeWorkingDaysPerWeek) {
        if (!isHomeOffice()) {
            this.homeWorkingDaysPerWeek = homeWorkingDaysPerWeek;
            this.virtualVideoConversationSize = 0;
            this.openNumberOfSeats = 0;
        }
    }

    public void becomeOpenOffice(int openNumberOfSeats) {
        if (!isOpenSpaceOffice()) {
            this.openNumberOfSeats = openNumberOfSeats;
            this.virtualVideoConversationSize = 0;
            this.homeWorkingDaysPerWeek = 0;
        }
    }

    public void becomeVirtualOffice(int virtualVideoConversationSize) {
        if (!isVirtualOffice()) {
            this.virtualVideoConversationSize = virtualVideoConversationSize;
            this.homeWorkingDaysPerWeek = 0;
            this.openNumberOfSeats = 0;
        }
    }

    public boolean isHomeOffice() {
        if (this.homeWorkingDaysPerWeek != 0) {
            return true;
        }
        return false;
    }

    public boolean isVirtualOffice() {
        if (this.virtualVideoConversationSize != 0) {
            return true;
        }
        return false;
    }

    public boolean isOpenSpaceOffice() {
        if (this.openNumberOfSeats != 0) {
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

    public void removeInvoice(Invoice invoice){
        if(this.getInvoice() != null){
            setInvoice(null);
            invoice.removeOffice();
        }
    }

    public void addOwner(Owner owner) {
        if (!owners.contains(owner)) {
            owners.add(owner);
            owner.addOffice(this);
        }
    }

    public void removeOwner(Owner owner){
        if(!owners.contains(owner)){
            owners.remove(owner);
            owner.removeOffice(this);
        }
    }

    public void addManagedByOwner(Owner owner) {
        if(!owners.contains(owner)){
            throw new IllegalArgumentException("Owner " +owner.getName() +" must be an owner of "+getName());
        }
        if(!managedByOwners.contains(owner)){
            managedByOwners.add(owner);
            owner.addOwnerManagesOffice(this);
        }
    }

    public void removeManagedByOwner(Owner owner){
        if(!managedByOwners.contains(owner)){
            managedByOwners.remove(owner);
            owner.removeOwnerManagesOffices(this);
        }
    }


    public void setLocation(Location location) {
        if(location==null){
            throw new IllegalArgumentException("Location cannot be null");
        }
        location.addOffice(this);
        this.location = location;

    }

    public void removeLocation() {
        if(this.location == null)
            return;
        this.location.removeOffice(this);
        this.location = null;
    }

    public boolean hasLocation() {
        return this.location !=null;
    }


}

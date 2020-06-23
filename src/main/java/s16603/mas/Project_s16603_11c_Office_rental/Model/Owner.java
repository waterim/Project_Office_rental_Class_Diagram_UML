package s16603.mas.Project_s16603_11c_Office_rental.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name="owner")
public class Owner extends Person{

    @NotBlank(message = "Place of birth can't be empty")
    private String placeOfBirth;

    @NotBlank(message = "Document can't be empty")
    private String document;

    @ManyToMany
    private Set<Office> offices = new HashSet<>();
    @ManyToMany
    private Set<Office> ownerManagesOffices = new HashSet<>();

//    private Set<Customer> customers = new HashSet<>();

        public Owner(@NotBlank(message = "Name can't be blank") String name, @NotBlank(message = "Surname can't be blank") String surname, @NotBlank(message = "Phone can't be blank") String phone, @NotNull(message = "Date of birth can't be blank") LocalDate dateOfBirth, @NotBlank(message = "Place of birth can't be empty") String placeOfBirth, @NotBlank(message = "Document can't be empty") String document) {
        super(name, surname, phone, dateOfBirth);
        this.placeOfBirth = placeOfBirth;
        this.document = document;
    }

    @Override
    public int calcAge() {
        return 0;
    }

    public void addOffice(Office office) {
        if (!offices.contains(office)) {
            offices.add(office);
            office.addOwner(this);
        }
    }

    public void removeOffice(Office office){
        if(!offices.contains(office)){
            offices.remove(office);
            office.removeOwner(this);
        }
    }

    public void addOwnerManagesOffice(Office office){
        if (!offices.contains(office)) {
            throw new IllegalArgumentException("Office " +office.getName() +" must have an owner");
        }

        if (!ownerManagesOffices.contains(office)) {
            ownerManagesOffices.add(office);
            office.addManagedByOwner(this);
        }
    }

    public void removeOwnerManagesOffices(Office office){
        if(!ownerManagesOffices.contains(office)){
            ownerManagesOffices.remove(office);
            office.removeManagedByOwner(this);
        }
    }

//    public void addCustomer(Customer customer) throws Exception{
//        if (!customers.contains(customer)) {
//            customers.add(customer);
//            customer.setOwner(this);
//        }
//    }
//
//    public void removeCustomer(Customer customer ) {
//        if (customers.contains(customer)) {
//            customers.remove(customer);
//        }
//    }
}

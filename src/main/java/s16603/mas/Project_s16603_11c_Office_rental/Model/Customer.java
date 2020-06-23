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
@Table(name="customer")
public class Customer extends Person{

    private int numberOfOffices;

    @NotBlank(message = "level of privilege can't be blank")
    private int levelOfPrivilege;

    @OneToMany
    private Set<Rental> rentalsSet=new HashSet<>();

    @Override
    public int calcAge() {
        return 0;
    }

//    //XOR
//    private Employee employee;
//    private Owner owner;

    public Customer(@NotBlank(message = "Name can't be blank") String name, @NotBlank(message = "Surname can't be blank") String surname, @NotBlank(message = "Phone can't be blank") String phone, @NotNull(message = "Date of birth can't be blank") LocalDate dateOfBirth, int numberOfOffices, @NotBlank(message = "level of privilege can't be blank") int levelOfPrivilege) {
        super(name, surname, phone, dateOfBirth);
        this.numberOfOffices = numberOfOffices;
        this.levelOfPrivilege = levelOfPrivilege;
    }
//    public Customer(@NotBlank(message = "Name can't be blank") String name, @NotBlank(message = "Surname can't be blank") String surname, @NotBlank(message = "Phone can't be blank") String phone,
//                    @NotNull(message = "Date of birth can't be blank") LocalDate dateOfBirth, int numberOfOffices, @NotBlank(message = "level of privilege can't be blank") int levelOfPrivilege, Employee employee) {
//        super(name, surname, phone, dateOfBirth);
//        this.numberOfOffices = numberOfOffices;
//        this.levelOfPrivilege = levelOfPrivilege;
//        this.employee = employee;
//    }
//    public Customer(@NotBlank(message = "Name can't be blank") String name, @NotBlank(message = "Surname can't be blank") String surname, @NotBlank(message = "Phone can't be blank") String phone,
//                    @NotNull(message = "Date of birth can't be blank") LocalDate dateOfBirth, int numberOfOffices, @NotBlank(message = "level of privilege can't be blank") int levelOfPrivilege, Owner owner) {
//        super(name, surname, phone, dateOfBirth);
//        this.numberOfOffices = numberOfOffices;
//        this.levelOfPrivilege = levelOfPrivilege;
//        this.owner = owner;
//    }

    public void addRental(Rental rental){
        if (rental == null) {
            throw new IllegalArgumentException("Rental cannot be null");
        }
        if (!this.rentalsSet.contains(rental)) {
            this.rentalsSet.add(rental);
            rental.setCustomer(this);
        }
    }

    public void createRental(Rental rental,Office office){
        office.addRental(rental);
        this.addRental(rental);
    }


    public void removeRental(Rental rental){
        if (rental == null) {
            throw new IllegalArgumentException("Rental cannot be null");
        }
        if (this.rentalsSet.contains(rental)) {
            if (this.rentalsSet.size()  == 1 ) {
                throw new IllegalArgumentException("can not remove the last rental from " + getName());
            }
            this.rentalsSet.remove(rental);
            rental.removeRental();
        }
    }
//    public void setCustomer(Customer customer) throws Exception {
//        if(this.owner != null){
//            throw new Exception("Owner is already set!");
//        }
//        if(this.employee == employee) return;
//        if(this.employee != null){
//            this.employee.removeCustomer(this);
//        }
//        this.employee = employee;
//        this.employee.addCustomer(this);
//    }
//
//    public void setOwner(Owner owner) throws Exception{
//        if(this.employee != null){
//            throw new Exception("Employee is already set!");
//        }
//        if(this.owner == owner) return;
//        if(this.owner != null){
//            this.owner.removeCustomer(this);
//        }
//        this.owner = owner;
//        this.owner.addCustomer(this);
//    }
}

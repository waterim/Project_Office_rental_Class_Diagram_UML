package s16603.mas.Project_s16603_11c_Office_rental.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
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
@Table(name="employee")
public class Employee extends Person{
    @Min(value = 2500)
    private double salary;

    @Min(value = 100)
    private double bonuses;

//    private Set<Customer> customers = new HashSet<>();

    public Employee(@NotBlank(message = "Name can't be blank") String name, @NotBlank(message = "Surname can't be blank") String surname, @NotBlank(message = "Phone can't be blank") String phone, @NotNull(message = "Date of birth can't be blank") LocalDate dateOfBirth, @Min(value = 2500) double salary, @Min(value = 100) double bonuses) {
        super(name, surname, phone, dateOfBirth);
        this.salary = salary;
        this.bonuses = bonuses;
    }

    @Override
    public int calcAge() {
        return 0;
    }

//    public void addCustomer(Customer customer) throws Exception{
//        if (!customers.contains(customer)) {
//            customers.add(customer);
//            customer.setEmployee(this);
//        }
//    }
//
//    public void removeCustomer(Customer customer ) {
//        if (customers.contains(customer)) {
//            customers.remove(customer);
//        }
//    }
}

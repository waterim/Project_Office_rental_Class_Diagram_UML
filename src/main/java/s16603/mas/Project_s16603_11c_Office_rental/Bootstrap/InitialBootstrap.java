package s16603.mas.Project_s16603_11c_Office_rental.Bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import s16603.mas.Project_s16603_11c_Office_rental.Model.*;
import s16603.mas.Project_s16603_11c_Office_rental.Repo.CustomerRepository;
import s16603.mas.Project_s16603_11c_Office_rental.Repo.EmployeeRepository;
import s16603.mas.Project_s16603_11c_Office_rental.Repo.OwnerRepository;
import s16603.mas.Project_s16603_11c_Office_rental.Repo.PersonRepository;

import java.time.LocalDate;

@Component
public class InitialBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private EmployeeRepository employeeRepo;
    private OwnerRepository ownerRepository;
    private PersonRepository personRepository;
    private CustomerRepository customerRepository;


    @Autowired
    public InitialBootstrap(EmployeeRepository employeeRepo,
                            OwnerRepository ownerRepository,
                            PersonRepository personRepository,
                            CustomerRepository customerRepository) {
        this.employeeRepo = employeeRepo;
        this.ownerRepository = ownerRepository;
        this.personRepository = personRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Employee employee1 = new Employee("emp1", "emp1", "12345", LocalDate.now(), 3000,150);
        Customer customer1 = new Customer("cust1", "cust1", "54321", LocalDate.now(), 0,1);
        employeeRepo.save(employee1);
        Office office = new Office("Office1", 1000, "office1 description",14, 12, 0,OfficeType.OPEN_SPACE, OfficeStatus.AVAILABLE);
        System.out.println(office);

        Rental rental = new Rental("rental1", LocalDate.now(), LocalDate.now(), customer1, office);
        System.out.println(rental);
        rental.removeRental();
        System.out.println(rental);
        System.out.println(employeeRepo.findAll());
    }
}

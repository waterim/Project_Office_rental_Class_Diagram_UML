package s16603.mas.Project_s16603_11c_Office_rental.Bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import s16603.mas.Project_s16603_11c_Office_rental.Model.*;
import s16603.mas.Project_s16603_11c_Office_rental.Repo.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitialBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private EmployeeRepository employeeRepo;
    private OwnerRepository ownerRepository;
    private PersonRepository personRepository;
    private CustomerRepository customerRepository;
    private OfficeRepository officeRepository;
    private LocationRepository locationRepository;
    private InvoiceRepository invoiceRepository;
    private PaymentRepository paymentRepository;
    private RentalRepository rentalRepository;


    @Autowired
    public InitialBootstrap(EmployeeRepository employeeRepo,
                            OwnerRepository ownerRepository,
                            PersonRepository personRepository,
                            CustomerRepository customerRepository,
                            OfficeRepository officeRepository,
                            LocationRepository locationRepository,
                            InvoiceRepository invoiceRepository,
                            PaymentRepository paymentRepository,
                            RentalRepository rentalRepository) {
        this.employeeRepo = employeeRepo;
        this.ownerRepository = ownerRepository;
        this.personRepository = personRepository;
        this.customerRepository = customerRepository;
        this.officeRepository = officeRepository;
        this.locationRepository = locationRepository;
        this.invoiceRepository = invoiceRepository;
        this.paymentRepository = paymentRepository;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Employee employee1 = new Employee("emp1", "emp1", "12345", LocalDate.now(), 3000,150);
        Customer customer1 = new Customer("cust1", "cust1", "54321", LocalDate.now(), 0,1);
        employeeRepo.save(employee1);
        customerRepository.save(customer1);
        Location location1 = new Location("loc1", "loc1");
        Location location2 = new Location("loc2", "loc2");
        Location location3 = new Location("loc3", "loc3");
        Location location4 = new Location("loc4", "loc4");
        Owner owner1 = new Owner("owner1", "owner1", "21341", LocalDate.now(), "123","412");
        Owner owner2 = new Owner("owner2", "owner2", "4234", LocalDate.now(), "321","1221413");
        Owner owner3 = new Owner("owner3", "owner3", "346345", LocalDate.now(), "2323","1241");
        Owner owner4 = new Owner("owner4", "owner4", "12312", LocalDate.now(), "245","12414");

        Set<Owner> owners1 = new HashSet<>();
        Set<Owner> owners2 = new HashSet<>();
        Set<Owner> owners3 = new HashSet<>();
        Set<Owner> owners4 = new HashSet<>();
        owners1.add(owner1);
        owners1.add(owner4);

        owners2.add(owner3);
        owners2.add(owner2);

        owners3.add(owner1);
        owners3.add(owner4);

        owners4.add(owner2);
        owners4.add(owner3);


        Office office1 = new Office("Office1", 1000, "office1 description",0, 12, 0,OfficeType.OPEN_SPACE, OfficeStatus.AVAILABLE, location1);
        Office office2 = new Office("Office2", 2000, "office2 description",7, 3, 0,OfficeType.VIRTUAL, OfficeStatus.RENT, location2);
        Office office3 = new Office("Office3", 3000, "office3 description",14, 12, 4,OfficeType.HOME, OfficeStatus.RENT, location3);
        Office office4 = new Office("Office4", 4000, "office4 description",1, 19, 2,OfficeType.OPEN_SPACE, OfficeStatus.AVAILABLE, location4);


        ownerRepository.save(owner1);
        ownerRepository.save(owner2);
        ownerRepository.save(owner3);
        ownerRepository.save(owner4);
        locationRepository.save(location1);
        locationRepository.save(location2);
        locationRepository.save(location3);
        locationRepository.save(location4);

        office1.setOwners(owners1);
        office2.setOwners(owners2);
        office3.setOwners(owners3);
        office4.setOwners(owners4);
        /////
        Invoice invoice = new Invoice(123);
        Payment payment = new Payment("13",21);
        paymentRepository.save(payment);
        invoice.addPayment(payment);
        invoiceRepository.save(invoice);
        office1.setInvoice(invoice);
        /////

        officeRepository.save(office1);
        officeRepository.save(office2);
        officeRepository.save(office3);
        officeRepository.save(office4);
        Rental rental = new Rental("Rental1", LocalDate.now(), LocalDate.now(), customer1, office1);
        rentalRepository.save(rental);
        office1.addRental(rental);
        System.out.println(officeRepository.findAll());
        System.out.println(employeeRepo.findAll());

    }
}

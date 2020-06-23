package s16603.mas.Project_s16603_11c_Office_rental.Bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import s16603.mas.Project_s16603_11c_Office_rental.Model.*;
import s16603.mas.Project_s16603_11c_Office_rental.Repo.*;

import java.time.LocalDate;

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


    @Autowired
    public InitialBootstrap(EmployeeRepository employeeRepo,
                            OwnerRepository ownerRepository,
                            PersonRepository personRepository,
                            CustomerRepository customerRepository,
                            OfficeRepository officeRepository,
                            LocationRepository locationRepository,
                            InvoiceRepository invoiceRepository,
                            PaymentRepository paymentRepository) {
        this.employeeRepo = employeeRepo;
        this.ownerRepository = ownerRepository;
        this.personRepository = personRepository;
        this.customerRepository = customerRepository;
        this.officeRepository = officeRepository;
        this.locationRepository = locationRepository;
        this.invoiceRepository = invoiceRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Employee employee1 = new Employee("emp1", "emp1", "12345", LocalDate.now(), 3000,150);
        Customer customer1 = new Customer("cust1", "cust1", "54321", LocalDate.now(), 0,1);
        employeeRepo.save(employee1);
        Location location = new Location("loc1", "loc1");
        locationRepository.save(location);
        Office office = new Office("Office1", 1000, "office1 description",14, 12, 0,OfficeType.OPEN_SPACE, OfficeStatus.AVAILABLE, location);
        Invoice invoice = new Invoice(123);
        Payment payment = new Payment("13",21);
        paymentRepository.save(payment);
        invoice.addPayment(payment);
        invoiceRepository.save(invoice);
        office.setInvoice(invoice);
        Owner owner = new Owner("mp1", "emp1", "12345", LocalDate.now(), "123","123");
        ownerRepository.save(owner);
        office.addOwner(owner);
        officeRepository.save(office);
        System.out.println(officeRepository.findAll());
        System.out.println(employeeRepo.findAll());
    }
}

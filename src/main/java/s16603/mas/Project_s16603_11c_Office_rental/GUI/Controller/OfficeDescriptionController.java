package s16603.mas.Project_s16603_11c_Office_rental.GUI.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import s16603.mas.Project_s16603_11c_Office_rental.GUI.View.OfficeDescriptionView;
import s16603.mas.Project_s16603_11c_Office_rental.Model.Office;
import s16603.mas.Project_s16603_11c_Office_rental.Model.Rental;
import s16603.mas.Project_s16603_11c_Office_rental.Repo.RentalRepository;

import javax.swing.*;

@Component
public class OfficeDescriptionController {

    private OfficeDescriptionView view;

    private JFrame window;


    private RentalRepository rentalRepository;

    @Autowired
    private OfficeListController officeListController;

    @Autowired
    private OrderController orderController;

    public OfficeDescriptionController(@Autowired RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
        view = new OfficeDescriptionView();
        initButtonListeners();
    }

    private void initButtonListeners() {
        view.getBtnBack().addActionListener(e -> {
            officeListController.showFromDescription(window);
        });
        view.getBtnOrder().addActionListener(e->{
            Iterable<Rental> rentals = rentalRepository.findAll();
            for(Rental r: rentals){
                orderController.showGUI(r, window);
                break;
            }

        });
    }


    public void showGUI(Office office, JFrame frame){
        this.window = frame;
        view.setData(office);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(view.getMainPanel());
    }
}

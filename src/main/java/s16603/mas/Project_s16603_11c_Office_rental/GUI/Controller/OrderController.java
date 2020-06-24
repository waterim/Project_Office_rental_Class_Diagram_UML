package s16603.mas.Project_s16603_11c_Office_rental.GUI.Controller;

import org.springframework.stereotype.Component;
import s16603.mas.Project_s16603_11c_Office_rental.GUI.View.OrderView;
import s16603.mas.Project_s16603_11c_Office_rental.Model.Rental;

import javax.swing.*;

@Component
public class OrderController {

    OrderView orderView;

    public OrderController() {
        orderView = new OrderView();
    }

    public void showGUI(Rental rental, JFrame frame){
        orderView.setData(rental);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(orderView.getMainPanel());
    }
}

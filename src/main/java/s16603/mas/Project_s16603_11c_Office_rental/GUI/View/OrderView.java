package s16603.mas.Project_s16603_11c_Office_rental.GUI.View;

import s16603.mas.Project_s16603_11c_Office_rental.Model.Rental;

import javax.swing.*;

public class OrderView {
    private JTextArea textOrderID;
    private JPanel mainPanel;

    public void setData(Rental data) {

        textOrderID.setText(String.valueOf(data.getRentalId()));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTextArea getTextOrderID() {
        return textOrderID;
    }

    public boolean isModified(Rental data) {
        return false;
    }
}

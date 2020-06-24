package s16603.mas.Project_s16603_11c_Office_rental.GUI.View;

import s16603.mas.Project_s16603_11c_Office_rental.Model.Office;

import javax.swing.*;

public class OfficeDescriptionView {
    private JTextPane nameText;
    private JTextPane priceText;
    private JTextPane descText;
    private JButton btnOrder;
    private JButton btnBack;
    private JLabel title;
    private JLabel name;
    private JLabel price;
    private JLabel description;
    private JPanel mainPanel;

    public void setData(Office data) {
        nameText.setText(data.getName());
        descText.setText(data.getDescription());
        priceText.setText(String.valueOf(data.getPrice()));
        nameText.setEditable(false);
        descText.setEditable(false);
        priceText.setEditable(false);
    }

    public void getData(Office data) {
        data.setName(nameText.getText());
        data.setDescription(descText.getText());
    }

    public JButton getBtnOrder() {
        return btnOrder;
    }

    public JButton getBtnBack() {
        return btnBack;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public boolean isModified(Office data) {
        if (nameText.getText() != null ? !nameText.getText().equals(data.getName()) : data.getName() != null)
            return true;
        if (descText.getText() != null ? !descText.getText().equals(data.getDescription()) : data.getDescription() != null)
            return true;
        return false;
    }
}

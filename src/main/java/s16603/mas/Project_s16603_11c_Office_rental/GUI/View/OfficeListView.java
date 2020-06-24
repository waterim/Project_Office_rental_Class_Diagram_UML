package s16603.mas.Project_s16603_11c_Office_rental.GUI.View;

import javafx.scene.control.ListCell;
import s16603.mas.Project_s16603_11c_Office_rental.Model.Office;
import s16603.mas.Project_s16603_11c_Office_rental.Model.Owner;

import javax.swing.*;
import java.awt.*;

public class OfficeListView {
    private JPanel mainPanel;
    private JLabel Title;
    private JLabel description;
    private JList offices;
    private JList owners;
    private JButton checkOfficeDescriptionButton;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JLabel getTitle() {
        return Title;
    }

    public JLabel getDescription() {
        return description;
    }

    public JList getOffices() {
        return offices;
    }


    public JList getOwners() {
        return owners;
    }

    public JButton getCheckOfficeDescriptionButton() {
        return checkOfficeDescriptionButton;
    }

    private void createUIComponents() {
        offices = new JList();
        offices.setCellRenderer(new OfficesCellRenderer());

        owners = new JList();
        owners.setCellRenderer(new OwnersCellRenderer());
    }

    private class OfficesCellRenderer extends JLabel implements ListCellRenderer<Office> {


        public OfficesCellRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Office> list, Office value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value.getName());
            if(isSelected){
                setForeground(list.getSelectionForeground());
                setForeground(list.getSelectionBackground());
            }else{
                setForeground(list.getForeground());
                setBackground(list.getBackground());
            }
            return this;
        }
    }

    private class OwnersCellRenderer extends JLabel implements ListCellRenderer<Owner> {


        public OwnersCellRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Owner> list, Owner value, int index, boolean isSelected, boolean cellHasFocus) {
            setText("Name: "+value.getName()+" Surname: "+value.getSurname()+" Phone: "+value.getPhone());
            if(isSelected){
                setForeground(list.getSelectionForeground());
                setForeground(list.getSelectionBackground());
            }else{
                setForeground(list.getForeground());
                setBackground(list.getBackground());
            }
            return this;
        }
    }
}

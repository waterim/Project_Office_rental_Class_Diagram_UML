package s16603.mas.Project_s16603_11c_Office_rental.GUI.Controller;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import s16603.mas.Project_s16603_11c_Office_rental.GUI.View.OfficeDescriptionView;
import s16603.mas.Project_s16603_11c_Office_rental.GUI.View.OfficeListView;
import s16603.mas.Project_s16603_11c_Office_rental.Model.Location;
import s16603.mas.Project_s16603_11c_Office_rental.Model.Office;
import s16603.mas.Project_s16603_11c_Office_rental.Model.Owner;
import s16603.mas.Project_s16603_11c_Office_rental.Repo.OfficeRepository;

import javax.swing.*;
import java.util.List;
import java.util.Set;

@Component
public class OfficeListController {
    private OfficeListView listView;


    private OfficeRepository officeRepository;

    @Autowired
    private OfficeDescriptionController officeDescriptionController;

    private JFrame mainWindow;

    public OfficeListController(@Autowired OfficeRepository officeRepository){
        this.officeRepository = officeRepository;
        listView = new OfficeListView();
        initListModels();
        initListListeners();
        initButtonListeners();
    }

    private void initButtonListeners() {
        listView.getCheckOfficeDescriptionButton().addActionListener(e -> {
            Office selectedValue = (Office) listView.getOffices().getSelectedValue();
            if(selectedValue != null){
                System.out.println("Selected office: "+selectedValue);
                officeDescriptionController.showGUI(selectedValue, mainWindow);
            }
        });
    }


    private void initListListeners() {
        listView.getOffices().addListSelectionListener(e -> {
            boolean isAdjusting = e.getValueIsAdjusting();
            if(!isAdjusting){
                Office selectedValue = (Office) listView.getOffices().getSelectedValue();
                System.out.println("OfficeListController.initListListeners isAdjusting "+isAdjusting+" selectedValue="+selectedValue);

                Office officeWithOwners = officeRepository.findById(selectedValue.getOfficeId()).get();
                System.out.println("Owners of an office:"+ officeWithOwners.getOwners());
                initOwnersListData(officeWithOwners.getOwners());
            }
        });
    }

    private void initOwnersListData(Set<Owner> owners) {
        DefaultListModel listModel = (DefaultListModel) listView.getOwners().getModel();
        listModel.removeAllElements();
        for(Owner o : owners){
            listModel.addElement(o);
        }

    }

    private void updateOfficeListFromDB() {
        Iterable<Office> offices = officeRepository.findAll();
        DefaultListModel<Office> officeModel = (DefaultListModel<Office>) listView.getOffices().getModel();
        for(Office o: offices){
            officeModel.addElement(o);
        }
    }

    private void initListModels() {
        listView.getOffices().setModel(new DefaultListModel<Office>());
        listView.getOwners().setModel(new DefaultListModel<Owner>());
    }

    public void showView(JFrame frame){
        this.mainWindow = frame;
        updateOfficeListFromDB();
        frame.getContentPane().removeAll();
        frame.getContentPane().add(listView.getMainPanel());
        frame.revalidate();
    }
    public void showFromDescription(JFrame frame){
        this.mainWindow = frame;
        frame.getContentPane().removeAll();
        frame.getContentPane().add(listView.getMainPanel());
        frame.revalidate();
    }

}

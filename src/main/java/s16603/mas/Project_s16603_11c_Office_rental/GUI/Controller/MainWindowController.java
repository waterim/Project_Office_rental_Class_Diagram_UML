package s16603.mas.Project_s16603_11c_Office_rental.GUI.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import s16603.mas.Project_s16603_11c_Office_rental.GUI.View.MainWindow;

import javax.swing.*;


@Component
public class MainWindowController {


//    private MainWindow mainWindow;
    private MainWindow mainWindow;

    @Autowired
    private OfficeListController officeListController;

    public MainWindowController(){
        mainWindow = new MainWindow();
        initMenuItemListener();
    }

    private void initMenuItemListener() {
        mainWindow.getMenuItemListOffices().addActionListener( e -> {
            SwingUtilities.invokeLater(() -> {
                officeListController.showView(mainWindow);
            });
        });
    }


    public void showGUI(){
        mainWindow.setVisible(true);
    }
}

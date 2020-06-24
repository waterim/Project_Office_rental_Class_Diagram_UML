package s16603.mas.Project_s16603_11c_Office_rental.GUI.View;

import javax.swing.*;
import java.awt.*;


public class MainWindow extends JFrame {

    private JMenuItem menuItemListOffices;

    public MainWindow(){
        setSize(1024,768);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Office rental");
        initMenus();
    }

    private void initMenus() {
        JMenuBar bar = new JMenuBar();
        JMenu officeMenu = new JMenu("Office");
        bar.add(officeMenu);
        menuItemListOffices = new JMenuItem("List of offices");
        officeMenu.add(menuItemListOffices);

        this.setJMenuBar(bar);
    }


    public JMenuItem getMenuItemListOffices() {
        return menuItemListOffices;
    }
}

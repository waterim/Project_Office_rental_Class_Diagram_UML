package s16603.mas.Project_s16603_11c_Office_rental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import s16603.mas.Project_s16603_11c_Office_rental.GUI.Controller.MainWindowController;

import javax.swing.*;


@SpringBootApplication
public class ProjectS1660311cOfficeRentalApplication {

	public static void main(String[] args) {
//		SpringApplication.run(ProjectS1660311cOfficeRentalApplication.class, args);
		ConfigurableApplicationContext context = new SpringApplicationBuilder(ProjectS1660311cOfficeRentalApplication.class)
				.headless(false).run(args);

		SwingUtilities.invokeLater(()->{
			context.getBean(MainWindowController.class).showGUI();
		});

	}

}

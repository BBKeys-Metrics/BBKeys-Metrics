package TestingMVC;

import java.util.Set;

import Metrics.Employee;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ScoresGUI {
	static private ScoresGUI instance= new ScoresGUI();
	
	private ScoresGUI() {
		
	}
	
	public static ScoresGUI getInstance() {
		return instance;
	}
	
	public Scene getScene() {
		GridPane grid = new GridPane();
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				Employee employee = Controller.getInstance().getUser();
				Set<Preference> prefs = Model.getInstance().getPreferences(employee);
				if (prefs != null) {
					Platform.runLater(new Runnable() {
						public void run() {
							for(Preference p : prefs) {
								Label name = new Label(p.getMetric().getName());
								grid.add(name, 0, 0);
							}
						}
					});
				}
			}
				
		});
		t1.start();
		grid.add(new Button("Press Me"), 1, 0);
		Scene scene = new Scene(grid, 300, 200);
		return scene;
	}
}

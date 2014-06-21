package TestingMVC;

import Metrics.Preferences;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ScoresScene {
	GridPane grid = new GridPane();
	
	public ScoresScene() {
		 
	}
	
	public Scene getScene() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				final Preferences pref = Model.getInstance().getPrefs("Rick");
				Platform.runLater(new Runnable() {
					public void run() {
						
						Label name = new Label(pref.getPreference(0).toString());
						grid.add(name, 0, 0);
					}
				});
				
			}
		});
		t1.start();
		grid.add(new Button("Press Me"), 1, 0);
		Scene scene = new Scene(grid, 300, 200);
		return scene;
	}
}

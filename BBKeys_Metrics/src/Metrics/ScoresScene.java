package Metrics;

import javafx.application.Platform;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class ScoresScene {
	GridPane grid = new GridPane();

	
	public ScoresScene() {
		 
	}
	
	public Scene getScene() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				Controller controller = Controller.getInstance();
				User user = controller.getUser();
				Preferences pref = controller.getPrefs(user);
				//grid.add(pref.view());
				Platform.runLater(new Runnable() {
					public void run() {
						Metrics.getInstance().getStage().show();
					}
				});
			}
		});
		t1.start();
		Scene scene = new Scene(grid, 300, 200);
		return scene;
	}

}

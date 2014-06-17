package Metrics;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class ScoresScene {
	public ScoresScene() {
		
	}
	
	public void setScene() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				Controller controller = Controller.getInstance();
				User user = controller.getUser();
				Preferences pref = controller.getPrefs(user);
				GridPane grid = new GridPane();
				Scene scene = new Scene(grid, 300,200);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Metrics.getInstance().getStage().setScene(scene);
					}
				});
			}
		});
		t1.start();
	}

}

package TestingMVC;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ScoresScene {
	GridPane grid = new GridPane();

	
	public ScoresScene() {
		 
	}
	
	public Scene getScene() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				Controller controller = Controller.getInstance();
				Label name = new Label("User Name:");
				grid.add(name, 0, 0);
				//Preferences pref = controller.getPrefs(user);
				//grid.add(pref.view());
				/*Platform.runLater(new Runnable() {
					public void run() {
						Controller.getInstance().getStage().show();
					}
				});*/
			}
		});
		t1.start();
		Scene scene = new Scene(grid, 300, 200);
		return scene;
	}
}

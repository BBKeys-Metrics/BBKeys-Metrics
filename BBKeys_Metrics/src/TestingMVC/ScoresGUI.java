package TestingMVC;

import java.util.Set;

import Metrics.Employee;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ScoresGUI {
	static private ScoresGUI instance= new ScoresGUI();
	
	/**
	 * Default Constructor
	 */
	private ScoresGUI() {
		
	}
	
	/**
	 * Getter method which returns the single instance of the ScoresGUI object
	 * @return ScoresGUI
	 */
	public static ScoresGUI getInstance() {
		return instance;
	}
	
	/**
	 * Getter method which returns the scene 
	 * @return Scene
	 */
	public Scene getScene() {
		GridPane grid = new GridPane();
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				Employee employee = Controller.getInstance().getEmployee();
				Set<Preference> prefs = Model.getInstance().getPreferences(employee, employee.getID());
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
		Scene scene = new Scene(grid);
		
		//get the image of the current user
        ImageView imView = new ImageView(Controller.getInstance().getEmployee().getPicture().getImage());
        //set width of image
        imView.setFitWidth(100);
        //keep scaling of image
        imView.setPreserveRatio(true);
        //add image to screen
        grid.add(imView, 0, 2, 1, 1);
        
        //set the style sheets (css) for the scene 
        //scene.getStylesheets().add(Metrics.class.getResource("../Metrics.css").toExternalForm());
        String originalStyle = "-fx-background-image: url(\"background.jpg\")";
        scene.getRoot().setStyle(originalStyle);
        return scene;
	}
}

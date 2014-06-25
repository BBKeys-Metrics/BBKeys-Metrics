package TestingMVC;

import Metrics.User;
import javafx.stage.Stage;

public class Controller {
	private static final Controller instance = new Controller();
	private Controller() {
	};
	
	public static Controller getInstance() {
		return instance;
	}
	
	public void initialize(Stage primaryStage) {
		getSettings();
		View.getInstance().start(primaryStage);
		Model.getInstance().setUser(new User("Richard", "password"));
	}
	
	private void getSettings() {
		//TODO: load settings from somewhere.
		//Location of database, first view, etc.
		System.out.println("Controller.getSettings() not implemented.");
	}

}


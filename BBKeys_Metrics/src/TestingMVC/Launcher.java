package TestingMVC;

import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {
	
	/**
	 * default constructor
	 */
	public Launcher() {
		
	};
	
	/**
	 * Main class (this is ran when the program is started)
	 * Calls the launch / start method
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * runs the initialize method of the Controller class
	 */
	@Override
	public void start(Stage arg0) {
		Controller.getInstance().initialize(arg0);
	}
}


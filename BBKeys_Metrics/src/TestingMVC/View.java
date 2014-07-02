package TestingMVC;

import Metrics.LoginGUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class View extends Application{
	private static final View instance = new View();
	private static Stage theStage;
	
	private View() {
	}
	
	public static View getInstance() {
		return instance;
	}
	
	public void start(Stage primaryStage) {
		theStage = primaryStage;
		ScoresScene scoreView = new ScoresScene();
		theStage.setScene(scoreView.getScene());
		theStage.show();
		//Login loginView = new Login();
		//loginView.start(theStage);
	}
	
	public Stage getStage() {
		return theStage;
	}

}

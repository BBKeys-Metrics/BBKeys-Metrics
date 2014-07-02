package TestingMVC;

import javafx.application.Application;
import javafx.scene.Scene;
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
		theStage.setScene(LoginGUI.getInstance().getScene());
		theStage.show();
		//Login loginView = new Login();
		//loginView.start(theStage);
	}
	
	public void setScene(Scene newScene) {
		theStage.setScene(newScene);
	}

}

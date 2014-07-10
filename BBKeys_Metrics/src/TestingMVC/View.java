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
		theStage.setMinWidth(500);
        theStage.setMinHeight(500);
        theStage.setWidth(500);
        theStage.setHeight(500);
		theStage.setScene(LoginGUI.getInstance().getScene());
		
		//theStage.setScene(MyScores.getInstance().getScene());
		theStage.show();
	}
	
	public void setScene(Scene newScene) {
		theStage.setScene(newScene);
	}

}

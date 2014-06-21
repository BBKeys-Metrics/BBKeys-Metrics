package TestingMVC;

import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {
	public Launcher() {
		
	};
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) {
		arg0.setScene(new ScoresScene().getScene());
		arg0.show();
	}
}


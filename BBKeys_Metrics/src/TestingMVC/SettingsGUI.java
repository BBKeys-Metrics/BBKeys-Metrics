package TestingMVC;

import Metrics.InitializeSettings;
import Metrics.Settings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

final public class SettingsGUI {
    //variables that will be used in the runnable classes 
	private static SettingsGUI instance = new SettingsGUI();
	private static Text actiontarget;
	
	private SettingsGUI() {
		
	}
	
	public Text getActionTarget() {
		return actiontarget;
	}
	/**
	 * getter method which returns the static instance of this class
	 * @return
	 */
	public static SettingsGUI getInstance() {
		return instance;
	}

	/**
	 * Return the Login scene
	 * @return Scene
	 */
    public Scene getScene() {
        //create the grid which will hold all of the elements
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
      //create text view which will prompt the user for a username
        Text hostLabel = new Text("Host:");
        hostLabel.setId("host"); //used with css
        grid.add(hostLabel, 0, 0, 1, 1);   //column, row, num columns spanned, num rows spanned
        
        //create the text field where the user will enter the username
        TextField host = new TextField();
        host.setPrefWidth(300);
        grid.add(host, 1, 0, 4, 1);
        
        //create the password text view
        Text portLabel = new Text("Port:");
        portLabel.setId("port");
        grid.add(portLabel, 0, 1, 1, 1);
        
        //create the port text field
        TextField port = new TextField();
        port.setPrefWidth(300);
        grid.add(port, 1, 1, 4, 1);
        
        Text databaseLabel = new Text("Database:");
        grid.add(databaseLabel, 0, 2, 1, 1);
        
        TextField database = new TextField();
        database.setPrefWidth(300);
        grid.add(database, 1, 2, 4, 1);
        
        Text userLabel = new Text("User:");
        grid.add(userLabel, 0, 3, 1, 1);
        
        TextField user = new TextField();
        user.setPrefWidth(300);
        grid.add(user, 1, 3, 4, 1);
        
        Text passwordLabel = new Text("Password:");
        grid.add(passwordLabel, 0, 4, 1, 1);
        
        PasswordField password = new PasswordField();
        password.setPrefWidth(300);
        grid.add(password, 1, 4, 4, 1);
        
        //save button
        Button btnSave = new Button("Save");
        btnSave.setAlignment(Pos.TOP_CENTER);
        grid.add(btnSave, 0, 5, 2, 1);

        Button btnCancel = new Button("Cancel");
        btnCancel.setAlignment(Pos.TOP_CENTER);
        grid.add(btnCancel, 0, 6, 2, 1);
        
        //error message
        actiontarget = new Text(); //no value for text so it won't appear in window until text is specified
        grid.add(actiontarget, 1, 5);
        actiontarget.setId("actiontarget");
        
        //action to be performed when the user hits the save button
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	Settings settings = new Settings(host.getText(), port.getText(), database.getText(), user.getText(), password.getText());
            	settings.start();
            }
        });
        
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	View.getInstance().setScene(LoginGUI.getInstance().getScene());
            }
        });
        
        //when the user is in the host field and hits enter, switch to the port field
        host.setOnAction((event) -> {       
        	port.requestFocus();
        });
        
        port.setOnAction((event) -> {       
        	database.requestFocus();
        });
        
        database.setOnAction((event) -> {       
        	user.requestFocus();
        });
        
        user.setOnAction((event) -> {       
        	password.requestFocus();
        });
        
        password.setOnAction((event) -> {       
        	btnSave.fire(); //perform btnSave action
        });
        
        new InitializeSettings(host, port, database, user, password).start();
        
        //set the size of the window
        Scene scene = new Scene(grid);
        
        //set the style sheets (css) for the scene 
        //scene.getStylesheets().add(Metrics.class.getResource("../Metrics.css").toExternalForm());
        String originalStyle = "-fx-background-image: url(\"background.jpg\")";
        scene.getRoot().setStyle(originalStyle);
        //start out with the text field having the focus
        host.requestFocus();
        return scene;
	}
}

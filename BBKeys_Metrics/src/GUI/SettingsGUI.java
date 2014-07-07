package GUI;

import Metrics.InitializeSettings;
import Metrics.Metrics;
import Metrics.Settings;
import javafx.application.Application;
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
import javafx.stage.Stage;

final public class SettingsGUI extends Application {
    //variables that will be used in the runnable classes 
	private static SettingsGUI instance;
	private Stage stage;
	private Scene scene;
	private String originalStyle;
	private int originalWidth;
	private int originalHeight;
	private Text actiontarget;
	private TextField host, port, database, user;
	private PasswordField password;
	
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
	 * getter method which returns the primary stage
	 * @return Stage
	 */
	public Stage getStage() {
		return stage;
	}
	
	/**
	 * getter method which returns the scene
	 * @return Scene
	 */
	public Scene getScene() {
		return scene;
	}
	
	/**
	 * getter method which returns the original style that was used
	 * @return String
	 */
	public String getOriginalStyleOfScene() {
		return originalStyle;
	}
	
	/**
	 * getter method which returns the original width of the screen
	 * @return int
	 */
	public int getOriginalWidth() {
		return originalWidth;
	}
	
	/**
	 * getter method which returns the original height of the screen
	 * @return int
	 */
	public int getOriginalHeight() {
		return originalHeight;
	}
		
	/**
	 * Runs the JavaFX Application
	 * @param primaryStage - the Stage that will be displayed
	 * @return void
	 */
    @Override
    public void start(Stage primaryStage) {
    	//initialize variables to be used by the calling classes
    	instance = this;
        
        //set the title of the window
        primaryStage.setTitle("Settings");
        
        //create the grid which will hold all of the elements
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        //set the minimum width and height of the window
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(500);
        
        //create text view which will prompt the user for a username
        Text hostLabel = new Text("Host:");
        hostLabel.setId("host"); //used with css
        grid.add(hostLabel, 0, 0, 1, 1);   //column, row, num columns spanned, num rows spanned
        
        //create the text field where the user will enter the username
        host = new TextField();
        host.setPrefWidth(300);
        grid.add(host, 1, 0, 4, 1);
        
        //create the password text view
        Text portLabel = new Text("Port:");
        portLabel.setId("port");
        grid.add(portLabel, 0, 1, 1, 1);
        
        //create the port text field
        port = new TextField();
        port.setPrefWidth(300);
        grid.add(port, 1, 1, 4, 1);
        
        Text databaseLabel = new Text("Database:");
        grid.add(databaseLabel, 0, 2, 1, 1);
        
        database = new TextField();
        database.setPrefWidth(300);
        grid.add(database, 1, 2, 4, 1);
        
        Text userLabel = new Text("User:");
        grid.add(userLabel, 0, 3, 1, 1);
        
        user = new TextField();
        user.setPrefWidth(300);
        grid.add(user, 1, 3, 4, 1);
        
        Text passwordLabel = new Text("Password:");
        grid.add(passwordLabel, 0, 4, 1, 1);
        
        password = new PasswordField();
        password.setPrefWidth(300);
        grid.add(password, 1, 4, 4, 1);
        
        //save button
        Button btnSave = new Button("Save");
        btnSave.setAlignment(Pos.TOP_CENTER);
        grid.add(btnSave, 0, 5, 2, 1);
        
        //error message
        actiontarget = new Text(); //no value for text so it won't appear in window until text is specified
        grid.add(actiontarget, 1, 5);
        actiontarget.setId("actiontarget");
        
        //action to be performed when the user hits the save button
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	Settings settings = new Settings(host.getText(), port.getText(), database.getText(), user.getText(), password.getText(), primaryStage);
            	settings.start();
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
                        
        //set the size of the window
        originalWidth = 500;
        originalHeight = 500;
        scene = new Scene(grid, originalWidth, originalHeight);
        
        //set the style sheets (css) for the scene 
        scene.getStylesheets().add(Metrics.class.getResource("../Metrics.css").toExternalForm());
        originalStyle = "-fx-background-image: url(\"background.jpg\")";
        scene.getRoot().setStyle(originalStyle);
        
        //display the window
        primaryStage.setScene(scene);
        primaryStage.show();
        
        new InitializeSettings(host, port, database, user, password).start();
        
        //start out with the host text field having the focus
        host.requestFocus();
	}
    
    /**
     * main method which launches the application
     * @param args - command line arguments
     * @return void
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * kill all threads when the program is closed
     * @return void
     */
    @Override
    public void stop() {
        System.exit(0);
    }
}

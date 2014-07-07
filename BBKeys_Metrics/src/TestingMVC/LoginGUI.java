package TestingMVC;

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

final public class LoginGUI {
    //variables that will be used in the runnable classes 
	private static LoginGUI instance = new LoginGUI();
	private static Text actiontarget;
	
	
	public Text getActionTarget() {
		return actiontarget;
	}
	/**
	 * getter method which returns the static instance of this class
	 * @return
	 */
	public static LoginGUI getInstance() {
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
        Text usernameLabel = new Text("Username:");
        usernameLabel.setId("username"); //used with css
        grid.add(usernameLabel, 0, 0, 1, 1);   //column, row, num columns spanned, num rows spanned
        
        //create the text field where the user will enter the username
        TextField username = new TextField();
        username.setPrefWidth(300);
        grid.add(username, 1, 0, 4, 1);
        
        //create the password text view
        Text passwordLabel = new Text("Password:");
        passwordLabel.setId("password");
        grid.add(passwordLabel, 0, 1, 1, 1);
        
        //create the password text field
        PasswordField password = new PasswordField();
        password.setPrefWidth(300);
        grid.add(password, 1, 1, 4, 1);
        
        //login button
        Button btnLogin = new Button("Login");
        btnLogin.setAlignment(Pos.TOP_CENTER);
        grid.add(btnLogin, 0, 2, 2, 1);
        
        //register as a new user button
        Button btnRegister = new Button("Register");
        btnRegister.setAlignment(Pos.TOP_CENTER);
        grid.add(btnRegister, 1, 2, 2, 1);
        
        Button btnSettings = new Button("Settings");
        btnSettings.setAlignment(Pos.TOP_CENTER);
        grid.add(btnSettings, 4, 2, 2, 1);
        
        //error message
        actiontarget = new Text(); //no value for text so it won't appear in window until text is specified
        grid.add(actiontarget, 1, 3);
        actiontarget.setId("actiontarget");
        
        
        //action to be performed when the user hits the login button
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {    
            	Login login = new Login(username.getText(), password.getText());
            	login.start();
            }
        });
        
        //when the user is in the username field and hits enter, switch to the password field
        username.setOnAction((event) -> {       
        	password.requestFocus();
        });
        
        //when the user is in the password field and hits enter, perform login button click
        password.setOnAction((event) -> {       
        	btnLogin.fire(); //perform login button click
        });
        
        //action to be performed when the user hits the register button
        btnRegister.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	//create new application and open in new window
                View.getInstance().setScene(RegisterGUI.getInstance().getScene());
            }
        });
        
      //action to be performed when the user hits the register button
        btnSettings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	//create new application and open in new window
                View.getInstance().setScene(SettingsGUI.getInstance().getScene());
            }
        });
        
        //set the size of the window
        Scene scene = new Scene(grid);
        
        //set the style sheets (css) for the scene 
        //scene.getStylesheets().add(Metrics.class.getResource("../Metrics.css").toExternalForm());
        String originalStyle = "-fx-background-image: url(\"background.jpg\")";
        scene.getRoot().setStyle(originalStyle);
        //start out with the text field having the focus
        username.requestFocus();
        return scene;
	}
}

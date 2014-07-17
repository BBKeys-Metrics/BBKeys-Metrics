package TestingMVC;

import Metrics.Register;
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

final public class RegisterGUI {
    //variables that will be used in the runnable classes 
	private static RegisterGUI instance = new RegisterGUI();
	private static Text actiontarget;
	
	/**
	 * Getter method which returns the error message object
	 * @return Text
	 */
	public Text getActionTarget() {
		return actiontarget;
	}
	
	/**
	 * getter method which returns the static instance of this class
	 * @return
	 */
	public static RegisterGUI getInstance() {
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
        
        //text which will prompt user for user id
        Text userIDLabel = new Text("User ID:");
        userIDLabel.setId("userid");
        grid.add(userIDLabel, 0, 0, 1, 1); //column, row, num columns spanned, num rows spanned
        
        //text field where the user will enter user id
        TextField userID = new TextField();
        userID.setPrefWidth(300);
        grid.add(userID, 1, 0, 4, 1);
        
        //create text view which will prompt the user for a username
        Text usernameLabel = new Text("Username:");
        usernameLabel.setId("username"); //used with css
        grid.add(usernameLabel, 0, 1, 1, 1);   //column, row, num columns spanned, num rows spanned
        
        //create the text field where the user will enter the username
        TextField username = new TextField();
        username.setPrefWidth(300);
        grid.add(username, 1, 1, 4, 1);
        
        //create the password text view
        Text passwordLabel = new Text("Password:");
        passwordLabel.setId("password");
        grid.add(passwordLabel, 0, 2, 1, 1);
        
        //create the password text field
        PasswordField password = new PasswordField();
        password.setPrefWidth(300);
        grid.add(password, 1, 2, 4, 1);
        
        //create the confirm password text view
        Text confirmPasswordLabel = new Text("Confirm Password:");
        confirmPasswordLabel.setId("confirm password");
        grid.add(confirmPasswordLabel, 0, 3, 1, 1);
        
        //create the confirm password text field
        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setPrefWidth(300);
        grid.add(confirmPassword, 1, 3, 4, 1);
        
        //when the user is in the user id text field and hits enter, go to the username text field
        userID.setOnAction((event) -> {       
        	username.requestFocus();
        });
        
        //when the user is in the username text field and hits enter, go to the password text field
        username.setOnAction((event) -> {       
        	password.requestFocus();
        });
        
        //when the user is in the password textfield and hits enter, go to the confrim password text field
        password.setOnAction((event) -> {       
        	confirmPassword.requestFocus();
        });
        
        //commit to the register button
        Button btnRegister = new Button("Register");
        btnRegister.setAlignment(Pos.TOP_CENTER);
        grid.add(btnRegister, 0, 4, 2, 1);
        
        Button btnCancel = new Button("Cancel");
        btnCancel.setAlignment(Pos.TOP_CENTER);
        grid.add(btnCancel, 0, 5, 2, 1);
        
        //error message
        actiontarget = new Text(); //no value for text so it won't appear in window until text is specified
        grid.add(actiontarget, 1, 5);
        actiontarget.setId("actiontarget");
        
        //action to perform when the commit button is pressed
        btnRegister.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	Register register = new Register(userID.getText(), username.getText(), password.getText(), confirmPassword.getText());
            	register.start();
            }
        });
        
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	View.getInstance().setScene(LoginGUI.getInstance().getScene());
            }
        });
        
        //when the user is in the confrim password field and hit enter, perform the commit button click
        confirmPassword.setOnAction((event) -> {       
        	btnRegister.fire(); //perform commit button click
        });
        
      //set the size of the window
        Scene scene = new Scene(grid);
        
        //set the style sheets (css) for the scene 
        //scene.getStylesheets().add(Metrics.class.getResource("../Metrics.css").toExternalForm());
        String originalStyle = "-fx-background-image: url(\"background.jpg\")";
        scene.getRoot().setStyle(originalStyle);
        //start out with the text field having the focus
        userID.requestFocus();
        return scene;
	}
}


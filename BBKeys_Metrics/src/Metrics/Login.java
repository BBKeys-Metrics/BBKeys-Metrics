package Metrics;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Application {
    //variables that will be used in the runnable classes 
	private static Login instance;
	private Stage stage;
	private Scene scene;
	private String originalStyle;
	private int originalWidth;
	private int originalHeight;
	private User user;
	
	/**
	 * getter method which returns the static instance of this class
	 * @return
	 */
	public static Login getInstance() {
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
        primaryStage.setTitle("Login");
        
        //create the grid which will hold all of the elements
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        /*
        //Allow elements to grow as the window is resized
        //grow horizontally
        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(20);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(20);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(60);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(0);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setPercentWidth(0);
        grid.getColumnConstraints().addAll(column0, column1, column2, column3, column4);
        
        //grow vertically
        RowConstraints rowConstraints0 = new RowConstraints();
        rowConstraints0.setPercentHeight(10);
        RowConstraints rowConstraints1 = new RowConstraints();
        rowConstraints1.setPercentHeight(5);
        RowConstraints rowConstraints2 = new RowConstraints();
        rowConstraints2.setPercentHeight(10);
        RowConstraints rowConstraints3 = new RowConstraints();
        rowConstraints3.setPercentHeight(65);
        RowConstraints rowConstraints4 = new RowConstraints();
        rowConstraints4.setPercentHeight(10);
        grid.getRowConstraints().addAll(rowConstraints0, rowConstraints1, rowConstraints2, rowConstraints3, rowConstraints4);
        */
        
        //set the minimum width and height of the window
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(500);
        
        //set the maximum width and height of the window
        //primaryStage.setMaxWidth(501);
        //primaryStage.setMaxHeight(501);
        
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
        
        //error message
        final Text actiontarget = new Text(); //no value for text so it won't appear in window until text is specified
        grid.add(actiontarget, 1, 3);
        actiontarget.setId("actiontarget");
        
        //action to be performed when the user hits the login button
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {    
            	boolean fieldsBlank = true;
            	boolean correctLogin = false;
            	boolean validUsername = false;
            	
            	DatabaseConnection dbCon = null;
            	ResultSet r = null;
            	
            	//check for empty fields
            	if (username.getText().equals("") || password.getText().equals("")) { //one or more fields were blank
            		actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("All fields are required");
            	}
            	else { //no fields were blank
            		fieldsBlank = false;
            	}
            	
            	if (!fieldsBlank) {
            		//set up connection to database
            		user = new User();
        			user.setUsername("MetricsApp");
        			user.setPassword("javafx");
        			try {
        				//connect to the database
						dbCon = new DatabaseConnection("SHANE-PC", "1433", "Metrics", user); 
						
	    				//check that the username exists
	    				r = dbCon.executeQuery("Select COUNT(employeeID) FROM Metrics.dbo.Users WHERE username = '" + username.getText() + "'");
	    				r.next();
	    				
	    				if (!r.getString(1).equals("1")) { //if there were no users with the specified username
	    					//display error message "Invalid Username"
	    					actiontarget.setFill(Color.FIREBRICK);
	                        actiontarget.setText("Invalid Username");
	    				}
	    				else { //there is a user with the specified username
	    					validUsername = true;
	    				}
        			} catch (SQLException e1) {
						e1.printStackTrace();
					}
            	}
            	
            	if (validUsername) {
    				try {
						//get the password for the specifiec user
	        			r = dbCon.executeQuery("Select password from Metrics.dbo.Users where username = '" + username.getText() + "'");
	        			r.next();
	        			String passwordFromDB = r.getString(1);
	        			
	        			//Check that an unencrypted password matches one that has previously been hashed
	        			if (!BCrypt.checkpw(password.getText(), passwordFromDB)) { //if the username and password combination were invalid
	        				//display error message "Invalid Password"
	        				actiontarget.setFill(Color.FIREBRICK);
	                        actiontarget.setText("Invalid Password");
	        			}
	    				else { //Username and Password combination were valid
	        				correctLogin = true;
	    				}
        			} catch (SQLException e1) {
						e1.printStackTrace();
					}
            	}
            	
            	if (correctLogin) { //if the Username and Password combination were valid
            		//redirect to user home page
    				Metrics metric = new Metrics();
                    metric.start(primaryStage);  //open in same window
            	}
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
            	Register register = new Register();
                register.start(primaryStage);  //open in same window
            }
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
        
        //start out with the text field having the focus
        username.requestFocus();
	}
    
    /**
     * getUser returns the user object for other classes to use.
     * @return User
     */
    public User getUser() {
    	return user;
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
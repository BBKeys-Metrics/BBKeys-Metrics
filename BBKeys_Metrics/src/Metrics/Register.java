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

public class Register extends Application {
    //variables that will be used in the runnable classes 
	private static Register instance;
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
	public static Register getInstance() {
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
        primaryStage.setTitle("Register");
        
        //create the grid which will hold all of the elements
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        //Allow elements to grow as the window is resized
        //grow horizontally
        /*
        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(30);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(30);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(40);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(0);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setPercentWidth(0);
        grid.getColumnConstraints().addAll(column0, column1, column2, column3, column4);
        
        //grow vertically
        RowConstraints rowConstraints0 = new RowConstraints();
        rowConstraints0.setPercentHeight(10);
        RowConstraints rowConstraints1 = new RowConstraints();
        rowConstraints1.setPercentHeight(10);
        RowConstraints rowConstraints2 = new RowConstraints();
        rowConstraints2.setPercentHeight(10);
        RowConstraints rowConstraints3 = new RowConstraints();
        rowConstraints3.setPercentHeight(10);
        RowConstraints rowConstraints4 = new RowConstraints();
        rowConstraints4.setPercentHeight(60);
        grid.getRowConstraints().addAll(rowConstraints0, rowConstraints1, rowConstraints2, rowConstraints3, rowConstraints4);
        */
        //set the minimum width and height of the window
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(500);
        
        Text userIDLabel = new Text("User ID:");
        userIDLabel.setId("userid");
        grid.add(userIDLabel, 0, 0, 1, 1);
        
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
        
      //create the password text view
        Text confirmPasswordLabel = new Text("Confirm Password:");
        confirmPasswordLabel.setId("confirm password");
        grid.add(confirmPasswordLabel, 0, 3, 1, 1);
        
        //create the password text field
        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setPrefWidth(300);
        grid.add(confirmPassword, 1, 3, 4, 1);
        
        //
        userID.setOnAction((event) -> {       
        	username.requestFocus();
        });
        
        username.setOnAction((event) -> {       
        	password.requestFocus();
        });
        
        password.setOnAction((event) -> {       
        	confirmPassword.requestFocus();
        });
        
        Button btnRegister = new Button("Commit");
        btnRegister.setAlignment(Pos.TOP_CENTER);
        grid.add(btnRegister, 0, 4, 2, 1);
        
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 5);
        actiontarget.setId("actiontarget");
        
        btnRegister.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	boolean fieldsBlank = true;
            	boolean passwordsMatch = false;
            	boolean duplicateID = true;
            	boolean validID = false;
            	boolean duplicateUsername = true;
            	ResultSet r = null;
            	DatabaseConnection dbCon = null;
            	
            	if (userID.getText().equals("") || username.getText().equals("") || password.getText().equals("") || confirmPassword.getText().equals("")) {
            		actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("All fields are required");
            	}
            	else {
            		fieldsBlank = false;
            	}
            	
            	if (!fieldsBlank) {
            		//compare first password field with second password field
                	String firstPassword = password.getText();
                	String secondPassword = confirmPassword.getText();
                	
                	if (!firstPassword.equals(secondPassword)) { //passwords don't match
                		//display error message "Passwords don't match"
                		actiontarget.setFill(Color.FIREBRICK);
                        actiontarget.setText("Passwords don't match");
                	}
                	else {
                		passwordsMatch = true;
                	}
            	}
                	
            	if (passwordsMatch) {
            		//check for duplicate id in database 
            		try {
            			user = new User();
            			user.setUsername("MetricsApp");
            			user.setPassword("javafx");
            			dbCon = new DatabaseConnection("SHANE-PC", "1433", "Metrics", user);	
            			r = dbCon.executeQuery("SELECT COUNT(employeeID) FROM Metrics.dbo.Users where employeeID = '" + userID.getText() + "'");
            			r.next();
            			
            			if (!r.getString(1).equals("0")) { //duplicate id found
            				actiontarget.setFill(Color.FIREBRICK);
                            actiontarget.setText("Duplicate ID Found");
            			}
            			else {
            				duplicateID = false;
            			}
            		} catch (SQLException e1) {
            			e1.printStackTrace();
            		}
            	}

            	//check for valid id (i.e. id exists in people)
            	if (!duplicateID) {
            		try {
						r = dbCon.executeQuery("SELECT COUNT(Peep_ID) FROM Metrics.dbo.People where Peep_ID = '" + userID.getText() + "'");
	        			r.next();
	            		if (!r.getString(1).equals("1")) {
	            			//display error message "Invalid ID"
	            			actiontarget.setFill(Color.FIREBRICK);
                            actiontarget.setText("Invalid ID");
	            		}
	            		else {
	            			validID = true;
	            		}
            		} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	}
            	
            	if (validID) {
            		//check database for duplicate username	
        			try {
						r = dbCon.executeQuery("SELECT COUNT(employeeID) FROM Metrics.dbo.Users where username = '" + username.getText() + "'");
	        			r.next();
	        			if (!r.getString(1).equals("0")) {
	        				actiontarget.setFill(Color.FIREBRICK);
	                        actiontarget.setText("Duplicate Username Found");
	        			}
	        			else {
	        				duplicateUsername = false;
	        			}
        			} catch (SQLException e1) {
						e1.printStackTrace();
					}
            	}
            	
            	if (!duplicateUsername){
            		String id = userID.getText();
    				String name = username.getText();
    				String pass = password.getText();
    				String encryptedPassword = "";
    				
    				//add userID, username, password to the users table in the database
    				try {
						dbCon.executeUpdate("INSERT INTO Metrics.dbo.Users (employeeID, username, password) VALUES (" + id + ", '" + name + "', '" + pass + "')");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
    				
    				//encrypt password
    				
    				//redirect to user home page
    				Metrics metric = new Metrics();
                    metric.start(primaryStage);  //open in same window
            	}        	
            }
        });
        
        confirmPassword.setOnAction((event) -> {       
        	btnRegister.fire(); //perform commit button click
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
        userID.requestFocus();
        
        
        
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

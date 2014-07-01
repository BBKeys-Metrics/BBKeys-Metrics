package Metrics;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Thread{
	
	private String username;
	private String password;
	private LoginGUI loginGUI;
	private Text actiontarget;
	private Stage primaryStage;
	
	public Login(String username, String password, Stage primaryStage) {
		this.username = username;
		this.password = password;
		loginGUI = LoginGUI.getInstance();
		actiontarget = loginGUI.getActionTarget();
		this.primaryStage = primaryStage;
	}
	
	@Override
	public void run() {
		boolean fieldsBlank = true;
    	boolean correctLogin = false;
    	boolean validUsername = false;
    	
    	DatabaseConnection dbCon = null;
    	ResultSet r = null;
    	
    	//check for empty fields
    	if (username.equals("") || password.equals("")) { //one or more fields were blank
    		Platform.runLater(new Runnable() {
            	@Override
            	public void run() {
            		actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("All fields are required");
            	}
            });	
    	}
    	else { //no fields were blank
    		fieldsBlank = false;
    	}
    	
    	if (!fieldsBlank) {
    		//set up connection to database
    		//user = new User();
			try {
				//connect to the database
				//dbCon = new DatabaseConnection("SHANE-PC", "1433", "Metrics", user); 
				dbCon = new DatabaseConnection();
				//check that the username exists
				r = dbCon.executeQuery("Select COUNT(employeeID) FROM Metrics.dbo.Users WHERE username = '" + username + "'");
				r.next();
				
				if (!r.getString(1).equals("1")) { //if there were no users with the specified username
					//display error message "Invalid Username"
					Platform.runLater(new Runnable() {
		            	@Override
		            	public void run() {
		            		actiontarget.setFill(Color.FIREBRICK);
		                    actiontarget.setText("Invalid Username");
		            	}
		            });
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
    			r = dbCon.executeQuery("Select password from Metrics.dbo.Users where username = '" + username + "'");
    			r.next();
    			String passwordFromDB = r.getString(1);
    			
    			//Check that an unencrypted password matches one that has previously been hashed
    			if (!BCrypt.checkpw(password, passwordFromDB)) { //if the username and password combination were invalid
    				//display error message "Invalid Password"
    				Platform.runLater(new Runnable() {
    	            	@Override
    	            	public void run() {
    	            		actiontarget.setFill(Color.FIREBRICK);
    	                    actiontarget.setText("Invalid Password");
    	            	}
    	            });
    			}
				else { //Username and Password combination were valid
    				correctLogin = true;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			if (correctLogin) {
				Platform.runLater(new Runnable() {
	            	@Override
	            	public void run() {
	            		Metrics metric = new Metrics();
	                    metric.start(primaryStage);  //open in same window
	            	}
	            });
				
			}
    	}
	}
}
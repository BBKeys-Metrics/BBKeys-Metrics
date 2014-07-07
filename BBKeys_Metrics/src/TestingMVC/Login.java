package TestingMVC;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;

import TestingMVC.LoginGUI;
import Metrics.BCrypt;
import Metrics.DatabaseConnection;

public class Login extends Thread{
	
	private String username;
	private String password;
	private LoginGUI loginGUI;
	private Text actiontarget;
	
	public Login(String username, String password) {
		this.username = username;
		this.password = password;
		loginGUI = LoginGUI.getInstance();
		actiontarget = loginGUI.getActionTarget();
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
			try {
				//connect to the database
				dbCon = DatabaseConnection.getInstance();
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
	            		//redirect to scores
	            		View.getInstance().setScene(ScoresGUI.getInstance().getScene());
	            	}
	            });
			}
    	}
	}
}
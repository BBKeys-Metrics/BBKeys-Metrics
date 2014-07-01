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

public class Register extends Thread {
	private String userID;
	private String username;
	private String password;
	private String confirmPassword;
	private RegisterGUI register;
	private Text actiontarget;
	private Stage primaryStage;
	
	public Register(String userID, String username, String password, String confirmPassword, Stage primaryStage) {
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.primaryStage = primaryStage;
		register = RegisterGUI.getInstance();
		actiontarget = register.getActionTarget();
	}

    @Override
    public void run() {
    	boolean fieldsBlank = true;
    	boolean passwordsMatch = false;
    	boolean duplicateID = true;
    	boolean validID = false;
    	boolean duplicateUsername = true;
    	ResultSet r = null;
    	DatabaseConnection dbCon = null;
    	
    	if (userID.equals("") || username.equals("") || password.equals("") || confirmPassword.equals("")) {
    		Platform.runLater(new Runnable() {
            	@Override
            	public void run() {
            		actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("All fields are required");
            	}
            });
    	}
    	else {
    		fieldsBlank = false;
    	}
    	
    	if (!fieldsBlank) {
    		//compare first password field with second password field
        	String firstPassword = password;
        	String secondPassword = confirmPassword;
        	
        	if (!firstPassword.equals(secondPassword)) { //passwords don't match
        		//display error message "Passwords don't match"
        		Platform.runLater(new Runnable() {
                	@Override
                	public void run() {
                		actiontarget.setFill(Color.FIREBRICK);
                        actiontarget.setText("Passwords don't match");
                	}
                });
        	}
        	else {
        		passwordsMatch = true;
        	}
    	}
        	
    	if (passwordsMatch) {
    		//check for duplicate id in database 
    		try {
    			dbCon = new DatabaseConnection();
    			r = dbCon.executeQuery("SELECT COUNT(employeeID) FROM Metrics.dbo.Users where employeeID = '" + userID + "'");
    			r.next();
    			
    			if (!r.getString(1).equals("0")) { //duplicate id found
    				Platform.runLater(new Runnable() {
    	            	@Override
    	            	public void run() {
    	            		actiontarget.setFill(Color.FIREBRICK);
    	                    actiontarget.setText("Duplicate ID Found");
    	            	}
    	            });
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
				r = dbCon.executeQuery("SELECT COUNT(Peep_ID) FROM Metrics.dbo.People where Peep_ID = '" + userID + "'");
    			r.next();
        		if (!r.getString(1).equals("1")) {
        			//display error message "Invalid ID"
        			Platform.runLater(new Runnable() {
                    	@Override
                    	public void run() {
                    		actiontarget.setFill(Color.FIREBRICK);
                            actiontarget.setText("Invalid ID");
                    	}
                    });
        		}
        		else {
        			validID = true;
        		}
    		} catch (SQLException e1) {
				e1.printStackTrace();
			}
    	}
    	
    	if (validID) {
    		//check database for duplicate username	
			try {
				r = dbCon.executeQuery("SELECT COUNT(employeeID) FROM Metrics.dbo.Users where username = '" + username + "'");
    			r.next();
    			if (!r.getString(1).equals("0")) {
    				Platform.runLater(new Runnable() {
    	            	@Override
    	            	public void run() {
    	            		actiontarget.setFill(Color.FIREBRICK);
    	                    actiontarget.setText("Duplicate Username Found");
    	            	}
    	            });
    			}
    			else {
    				duplicateUsername = false;
    			}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
    	}
    	
    	if (!duplicateUsername){
    		String id = userID;
			String name = username;
			String pass = password;
			
			//encrypt password
			String encryptedPassword = "";
			
			// Hash a password for the first time
			encryptedPassword = BCrypt.hashpw(pass, BCrypt.gensalt());
			
			//add userID, username, password to the users table in the database
			try {
				dbCon.executeUpdate("INSERT INTO Metrics.dbo.Users (employeeID, username, password) VALUES (" + id + ", '" + name + "', '" + encryptedPassword + "')");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			Platform.runLater(new Runnable() {
            	@Override
            	public void run() {
            		//redirect to user home page
        			Metrics metric = new Metrics();
                    metric.start(primaryStage);  //open in same window
            	}
            });
			
    	}        	
    }
}

package Metrics;

import TestingMVC.LoginGUI;
import TestingMVC.Model;
import TestingMVC.RegisterGUI;
import TestingMVC.View;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Register extends Thread {
	private String userID;
	private String username;
	private String password;
	private String confirmPassword;
	private RegisterGUI register;
	private Text actiontarget;
	
	public Register(String userID, String username, String password, String confirmPassword) {
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
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
    	
    	//check for empty fields
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
        	if (!password.equals(confirmPassword)) { //passwords don't match
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
    		duplicateID = Model.getInstance().duplicateIDFound(userID);
    		if (duplicateID) {
    			Platform.runLater(new Runnable() {
	            	@Override
	            	public void run() {
	            		actiontarget.setFill(Color.FIREBRICK);
	                    actiontarget.setText("Duplicate ID Found");
	            	}
	            });
    		}
    	}

    	//check for valid id (i.e. id exists in people)
    	if (!duplicateID) {
    		validID = Model.getInstance().validID(userID);
    		if (!validID) {
    			Platform.runLater(new Runnable() {
                	@Override
                	public void run() {
                		actiontarget.setFill(Color.FIREBRICK);
                        actiontarget.setText("Invalid ID");
                	}
                });
    		}
    	}
    	
    	if (validID) {
    		duplicateUsername = Model.getInstance().duplicateUsername(username);
    		if (duplicateUsername) {
    			Platform.runLater(new Runnable() {
	            	@Override
	            	public void run() {
	            		actiontarget.setFill(Color.FIREBRICK);
	                    actiontarget.setText("Duplicate Username Found");
	            	}
	            });
    		}
    	}
    	
    	if (!duplicateUsername){
			//encrypt password
			String encryptedPassword = "";
			
			// Hash a password for the first time
			encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
			
			//add userID, username, password to the users table in the database
			Model.getInstance().addNewUser(userID, username, encryptedPassword);
			
			Platform.runLater(new Runnable() {
            	@Override
            	public void run() {
            		//redirect to user home page
            		View.getInstance().setScene(LoginGUI.getInstance().getScene());
            	}
            });
    	}        	
    }
}

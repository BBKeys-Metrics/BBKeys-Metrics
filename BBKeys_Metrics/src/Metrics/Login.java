package Metrics;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


import TestingMVC.Controller;
import TestingMVC.LoginGUI;
import TestingMVC.ScoresGUI;
import TestingMVC.View;
import TestingMVC.Model;

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
			validUsername = Model.getInstance().usernameExists(username);
			if (!validUsername) {
				Platform.runLater(new Runnable() {
	            	@Override
	            	public void run() {
	            		actiontarget.setFill(Color.FIREBRICK);
	                    actiontarget.setText("Invalid Username");
	            	}
	            });
			}
    	}
    	
    	if (validUsername) {
			correctLogin = Model.getInstance().correctLogin(username, password);
			if (!correctLogin) {
				Platform.runLater(new Runnable() {
	            	@Override
	            	public void run() {
	            		actiontarget.setFill(Color.FIREBRICK);
	                    actiontarget.setText("Invalid Password");
	            	}
	            });
			}
			if (correctLogin) {
				Platform.runLater(new Runnable() {
	            	@Override
	            	public void run() {
	            		//Store the username
	            		Controller.getInstance().setUser(username);
	            		
	            		//redirect to scores
	            		View.getInstance().setScene(ScoresGUI.getInstance().getScene());
	            	}
	            });
			}
    	}
	}
}
package Metrics;

import TestingMVC.Model;
import TestingMVC.LoginGUI;
import TestingMVC.SettingsGUI;
import TestingMVC.Controller;
import TestingMVC.View;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Settings extends Thread{
	
	private String host;
	private String port;
	private String database;
	private String user;
	private String password;
	private SettingsGUI settingsGUI;
	private Text actiontarget;
	
	public Settings(String host, String port, String database, String user, String password) {
		this.host = host;
		this.port = port;
		this.database = database;
		this.user = user;
		this.password = password;
		settingsGUI = SettingsGUI.getInstance();
		actiontarget = settingsGUI.getActionTarget();
	}
	
	@Override
	public void run() {	
		boolean fieldsBlank = true;
		boolean validConnection = false;
		
		if (host.equals("") || port.equals("") || database.equals("") || user.equals("") || password.equals("")) { //one or more fields were blank
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
			//check to see if the specified connection is valid
			validConnection = Model.getInstance().isValidConnection(host, port, database, user, password);
			if (!validConnection) {
				Platform.runLater(new Runnable() {
	            	@Override
	            	public void run() {
	            		//display error message "Invalid Connection"
	            		actiontarget.setFill(Color.FIREBRICK);
	                    actiontarget.setText("Invalid Connection");
	            	}
	            });
			}
		}
		
		//the connection inputs were valid
		if (validConnection) {
			//save the settings to the databaseConnection.properties file
			Controller.getInstance().setConnectionStrings(host, port, database, user, password);
			
			//redirect the user to the login page
			Platform.runLater(new Runnable() {
            	@Override
            	public void run() {
            		View.getInstance().setScene(LoginGUI.getInstance().getScene());
            	}
            });
		}
	}
}

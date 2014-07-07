package Metrics;

import java.io.IOException;
import java.util.Properties;

import javafx.application.Platform;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class InitializeSettings extends Thread{
	
	private TextField host;
	private TextField port;
	private TextField database;
	private TextField user;
	private PasswordField password;
	
	public InitializeSettings(TextField host, TextField port, TextField database, TextField user, PasswordField password) {
		this.host = host;
		this.port = port;
		this.database = database;
		this.user = user;
		this.password = password;
	}
	
	@Override
	public void run() {	
		//read in the properties file
		Properties properties = new Properties();
		try {
			//fill properties with the data from the file
			properties.load(User.class.getResourceAsStream("../databaseConnection.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//get values
		String hostString = properties.getProperty("host");
		String portString = properties.getProperty("port");
		String databaseString = properties.getProperty("database");
		String userString = properties.getProperty("username");
		String passwordString = properties.getProperty("password");
		
		//set the value of the fields equal to what was already in the properties file
		Platform.runLater(new Runnable() {
        	@Override
        	public void run() {
                host.setText(hostString);
                port.setText(portString);
                database.setText(databaseString);
                user.setText(userString);
                password.setText(passwordString);
        	}
        });	
	}
}

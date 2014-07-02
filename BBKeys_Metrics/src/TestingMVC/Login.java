package TestingMVC;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Login extends Thread{
	
	private String username;
	private String password;
	private Text actiontarget;
	
	public Login(String username, String password) {
		this.username = username;
		this.password = password;
		actiontarget = LoginGUI.getInstance().getActionTarget();
	}
	
	@Override
	public void run() {
		boolean fieldsBlank = true;
    	
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
			//connect to the database
			if (Model.getInstance().login(username, password)) {
				Platform.runLater(new Runnable() {
	            	@Override
	            	public void run() {
	            		View.getInstance().setScene(ScoresGUI.getInstance().getScene());
	            	}
	            });
			}
			else {
				Platform.runLater(new Runnable() {
	            	@Override
	            	public void run() {
	            		actiontarget.setFill(Color.FIREBRICK);
	                    actiontarget.setText("Invalid Username or Password");
	            	}
	            });
			}	
    	}
	}
}
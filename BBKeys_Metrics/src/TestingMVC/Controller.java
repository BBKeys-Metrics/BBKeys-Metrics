package TestingMVC;

import Metrics.Preferences;
import Metrics.User;

public class Controller {
	private static final Controller instance = new Controller();
	
	private Controller() {
	};
	
	public static Controller getInstance() {
		return instance;
	}

	public User getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public Preferences getPrefs(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void main(String[] args){
		View.launch(args);
	}
}


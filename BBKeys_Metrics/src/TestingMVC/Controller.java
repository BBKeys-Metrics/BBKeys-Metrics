package TestingMVC;

import Metrics.Preferences;

public class Controller {
	private static final Controller instance = new Controller();
	private Controller() {
	};
	
	public static Controller getInstance() {
		return instance;
	}

	public Preferences getPrefs(String user) {
		return Model.getInstance().getPrefs(user);
	}
}


package Metrics;

public class Controller {
	private static final Controller instance = new Controller();
	
	private Controller() {
	};
	
	public static Controller getInstance() {
		return instance;
	}
}

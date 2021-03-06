package TestingMVC;


import java.util.ArrayList;
import java.util.List;

import Metrics.Employee;
import Metrics.Leader;
import Metrics.Metric;
import javafx.stage.Stage;

public class Controller {
	private static final Controller instance = new Controller();
	private Employee user;	
	private int numToDisplay;
	private List<Metric> allMetrics = new ArrayList<Metric>();
	
	/**
	 * Gets the number of people to display in the leaderboard
	 * @return int
	 */
	public int getNumToDisplay() {
		return numToDisplay;
	}
	
	/**
	 * default constructor
	 */
	private Controller() {
	};
	
	/**
	 * Gets the instance of the class
	 * @return Controller
	 */
	public static Controller getInstance() {
		return instance;
	}
	
	/**
	 * Gets the Employee object of the current user
	 * @return Employee
	 */
	public Employee getEmployee(){
		return user;
	}
	
	/**
	 * Gets everything set up and then sets the stage
	 * @param primaryStage
	 */
	public void initialize(Stage primaryStage) {
		getSettings();
		View.getInstance().start(primaryStage);
	}
	
	/**
	 * Get the number of people to display in the leaderboard
	 */
	public void getSettings() {
		numToDisplay = Model.getInstance().getSettings();
	}
	
	/**
	 * Gets a single metric based on id
	 * @param ID
	 * @return Metric
	 */
	public Metric getMetricByID(int ID) {
		if (allMetrics.isEmpty()) {
			allMetrics = Model.getInstance().getMetrics();
		}
		for(Metric m : allMetrics) {
			if (m.getID() == ID) {
				return m;
			}
		}
		return null;
	}

	/**
	 * Getter method which returns a single metric based on name
	 * @param name
	 * @return Metric
	 */
	public Metric getMetricByName(String name) {
		if (allMetrics.isEmpty()) {
			allMetrics = Model.getInstance().getMetrics();
		}
		for(Metric m : allMetrics) {
			if (m.getName().equals(name)) {
				return m;
			}
		}
		return null;
	}
	
	/**
	 * Gets a single Employee based on username
	 * @param name
	 * @return Employee
	 */
	public Employee getEmployeeByName(String name) {
		String id = Model.getInstance().getEmployeeIDByUsername(name);
		return Model.getInstance().getEmployeeByID(id);	
	}
	
	/**
	 * Changes the database connection to the new values passed in as parameters
	 * @param host
	 * @param port
	 * @param database
	 * @param username
	 * @param password
	 */
	public void setConnectionStrings(String host, String port, String database, String username, String password) {		
		Model.getInstance().setUpConnection(host, port, database, username, password);
	}
	
	/**
	 * Sets the current user's username
	 * @param username
	 */
	public void setUser(String username) {
		String empID = Model.getInstance().getEmployeeIDByUsername(username);
		user = Model.getInstance().getEmployeeByID(empID);
	}

	/**
	 * Gets the Set of Metrics
	 * @return Set<Metric>
	 */
	public List<Metric> getMetrics() {
		if (allMetrics.isEmpty()) {
			allMetrics = Model.getInstance().getMetrics();
		}
		return allMetrics;
	}
	
	/**
	 * Gets the top x employees for the given unit.  Returns a list of employee objects.
	 * @param timeUnit
	 * @param metric
	 * @return Leader List
	 */
	public List<Leader> getTopLeaders(Metric metric, TimeSpan timeUnit){
		return Model.getInstance().getTopLeaders(metric, timeUnit);
	}

}


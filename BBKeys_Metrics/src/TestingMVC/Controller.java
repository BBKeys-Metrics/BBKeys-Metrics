package TestingMVC;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Metrics.Employee;
import Metrics.Leader;
import Metrics.Metric;
import Metrics.MetricScore;
import javafx.stage.Stage;

public class Controller {
	private static final Controller instance = new Controller();
	private Employee user;	
	private int numToDisplay;
	private Set<Metric> allMetrics = new HashSet<Metric>();
	
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
	private void getSettings() {
		numToDisplay = Model.getInstance().getSettings();
	}
	
	/**
	 * 
	 * @param ID
	 * @return
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
		Metric toAdd = Model.getInstance().getMetricByID(ID);
		if (toAdd != null) {
			allMetrics.add(toAdd);
		}
		return toAdd;
	}
	
	public Employee getEmployeeByName(String name) {
		String id = Model.getInstance().getEmployeeIDByUsername(name);
		return Model.getInstance().getEmployeeByID(id);	
	}
	
	public void setConnectionStrings(String host, String port, String database, String username, String password) {		
		Model.getInstance().setUpConnection(host, port, database, username, password);
	}
	
	public void setUser(String username) {
		String empID = Model.getInstance().getEmployeeIDByUsername(username);
		user = Model.getInstance().getEmployeeByID(empID);
	}

	public Set<Metric> getMetrics() {
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


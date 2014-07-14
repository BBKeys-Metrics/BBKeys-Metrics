package TestingMVC;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Metrics.Employee;
import Metrics.Metric;
import Metrics.MetricScore;
import javafx.stage.Stage;

public class Controller {
	private static final Controller instance = new Controller();
	private Employee user;	
	private int numToDisplay;
	private Set<Metric> allMetrics = new HashSet<Metric>();
	
	private Controller() {
	};
	
	public static Controller getInstance() {
		return instance;
	}
	
	public Employee getEmployee(){
		return user;
	}
	
	public void initialize(Stage primaryStage) {
		getSettings();
		View.getInstance().start(primaryStage);
	}
	
	private void getSettings() {
		//TODO: load settings from somewhere.
		//Location of database, first view, etc.
		numToDisplay = Model.getInstance().getSettings();
	}
	
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
	 * Gets the score for a given metric, for the currently logged in user (employee).
	 * Returns the correct average score for the given time unit (day, week, month, ect)
	 * @param metric
	 * @param timeUnit
	 */
	public MetricScore getEmployeeScore(Metric metric, TimeSpan timeUnit) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the top score  for a given metric, for the given time unit (day, week, month, ect).
	 * Returns a MetricScore object with the retrieved score.
	 * @param metric
	 * @param timeUnit
	 * @return MetricScore
	 */
	public MetricScore getAverage(Metric metric, TimeSpan timeUnit) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the top score for a given metric, given the time unit (day, week, month, ect).
	 * @param metric
	 * @param timeUnit
	 * @param rank
	 * @return MetricScore
	 */
	public MetricScore getLeaderScore(Metric metric, TimeSpan timeUnit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Gets the top x employees for the given unit.  Returns a list of employee objects.
	 * @param timeUnit
	 * @param metric
	 * @return Employee List
	 */
	public List<Employee> getTopEmployees(Metric metric, TimeSpan timeUnit){
		// TODO Auto-generated method stub
		
		return null;
	}

}


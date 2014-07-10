package TestingMVC;


import java.util.Set;
import Metrics.DatabaseConnection;
import Metrics.Employee;
import Metrics.Metric;
import Metrics.MetricScore;
import javafx.stage.Stage;

public class Controller {
	private static final Controller instance = new Controller();
	private Employee user;	
	private int numToDisplay;
	private Set<Metric> allMetrics;
	
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

		
		System.out.println(numToDisplay);
	}
	
	public Metric getMetricByID(int ID) {
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
		DatabaseConnection.getInstance().setUpConnection(host, port, database, username, password);
	}
	
	public void setUser(String username) {
		String empID = Model.getInstance().getEmployeeIDByUsername(username);
		user = Model.getInstance().getEmployeeByID(empID);
	}

	public void getMetrics() {
		// TODO Auto-generated method stub
		
	}

	public MetricScore getEmployeeScore() {
		// TODO Auto-generated method stub
		return null;
	}

	public MetricScore getAverage() {
		// TODO Auto-generated method stub
		return null;
	}

	public MetricScore getTopScore() {
		// TODO Auto-generated method stub
		return null;
	}

}


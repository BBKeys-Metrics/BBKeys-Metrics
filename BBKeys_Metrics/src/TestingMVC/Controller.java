package TestingMVC;

import java.sql.ResultSet;
import java.sql.SQLException;

import Metrics.DatabaseConnection;
import Metrics.Employee;
import Metrics.GradableItem;
import Metrics.Metric;
import Metrics.MetricScore;
import javafx.stage.Stage;

public class Controller {
	private static final Controller instance = new Controller();
	private Employee user;	
	private String numToDisplay;
	
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
		
		
		ResultSet rs = Model.getInstance().getSettings();
		try {
			rs.next();
			numToDisplay = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(numToDisplay);
	}
	
	public Metric getMetricByID(Integer ID) {
		return null;
	}
	
	public Employee getEmployeeByName(String name) {
		String id = ResultSetBuilder.buildID(Model.getInstance().getEmployeeIDByName(name));
		return ResultSetBuilder.buildEmployee(Model.getInstance().getEmployeeByID(id));	
	}
	
	public void setConnectionStrings(String host, String port, String database, String username, String password) {		
		DatabaseConnection.getInstance().setUpConnection(host, port, database, username, password);
	}
	
	public void setUser(String username) {
		String employeeID = "";
		String employeeName = "";
		System.out.println("Setting user");
		ResultSet rs = Model.getInstance().getEmployeeIDByName(username);
		try {
			rs.next();
			employeeID = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		rs = Model.getInstance().getEmployeeByID(employeeID);
		try {
			rs.next();
			employeeName = rs.getString(1) + " " + rs.getString(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		user = new Employee(employeeName, employeeID, null);
		
	}

	public GradableItem getEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

	public void getAllMetrics() {
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


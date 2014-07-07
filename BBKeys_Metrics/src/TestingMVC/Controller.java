package TestingMVC;

import java.sql.ResultSet;
import java.sql.SQLException;

import Metrics.DatabaseConnection;
import Metrics.Employee;
import Metrics.Metric;
import javafx.stage.Stage;

public class Controller {
	private static final Controller instance = new Controller();
	private Employee user;	
	
	private Controller() {
	};
	
	public static Controller getInstance() {
		return instance;
	}
	
	public Employee getUser(){
		return user;
	}
	
	public void initialize(Stage primaryStage) {
		getSettings();
		View.getInstance().start(primaryStage);
	}
	
	private void getSettings() {
		//TODO: load settings from somewhere.
		//Location of database, first view, etc.
		System.out.println("Controller.getSettings() not implemented.");
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
		System.out.println("Setting user");
		ResultSet rs = Model.getInstance().getEmployeeIDByName(username);
		try {
			rs.next();
			//System.out.println(rs.getString(1));
			user = new Employee(rs.getString(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}


package TestingMVC;

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

}


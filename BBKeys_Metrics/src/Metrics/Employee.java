package Metrics;

import java.sql.SQLException;


public class Employee extends GradableItem{
	private EmployeePic picture;
	private String id;
	private User user = new User("sa", "SQL2k8#1");
	private DatabaseConnection dbCon = null;
	
	/**
	 * Constructor - Creates the new employee object and initializes the picture of the employee
	 * @param id
	 */
	public Employee(String id) {
		//create the database connection
		try {
			dbCon = new DatabaseConnection("SHANE-PC", "1433", "Metrics", user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//initialize the employee id
		this.id = id;
		
		//initialize the employee picture
		picture = new EmployeePic(dbCon.getConnection(), id);
	}
	
	/**
	 * Set the value of the picture for the Employee 
	 * @param picture - the new value of the picture
	 * @return void
	 */
	public void setPicture(EmployeePic picture) {
		this.picture = picture;
	}
	
	/**
	 * Get the value of the picture for the Employee
	 * @return EmployeePic
	 */
	public EmployeePic getPicture() {
		return picture;
	}
	
	public String getID() {
		return id;
	}
	
	public void setID(String id) {
		this.id = id;
	}
}

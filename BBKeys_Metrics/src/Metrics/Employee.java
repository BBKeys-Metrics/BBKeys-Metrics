package Metrics;

import java.sql.SQLException;
import java.util.Set;


public class Employee extends GradableItem{
	//initialize private variables
	private EmployeePic picture;
	private String id;
	
	/**
	 * Constructor
	 * @param name
	 * @param id
	 * @param scores
	 */
	public Employee(String name, String id, Set<MetricScore> scores) {
		super(name, scores);
		picture = new EmployeePic(id);
		this.id = id;
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
	
	/**
	 * Gets the value of the id
	 * @return String
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * Sets the value of the id
	 * @param id
	 */
	public void setID(String id) {
		this.id = id;
	}
}

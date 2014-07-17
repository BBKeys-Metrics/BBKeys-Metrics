package Metrics;

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
}

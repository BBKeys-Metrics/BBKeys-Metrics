package Metrics;


public class Employee extends GradableItem{
	private EmployeePic picture;
	private String id;
	
	public Employee(String id) {
		this.id = id;
		picture = null;
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
	 * @return byte[]
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

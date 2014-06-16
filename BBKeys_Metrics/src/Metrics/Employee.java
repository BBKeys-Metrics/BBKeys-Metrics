package Metrics;


public class Employee extends GradableItem{
	private byte[] picture;
	
	/**
	 * Constructor (initializes the private variables)
	 */
	public Employee() {
		picture = null;
	}
	
	/**
	 * Set the value of the picture for the Employee 
	 * @param picture - the new value of the picture
	 * @return void
	 */
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	
	/**
	 * Get the value of the picture for the Employee
	 * @return byte[]
	 */
	public byte[] getPicture() {
		return picture;
	}
}

package Metrics;


public class Employee extends GradableItem{
	private byte[] picture;
	private int id;
	
	/**
	 * Constructor (initializes the private variables)
	 */
	public Employee() {
		picture = null;
		id = 0;
	}
	
	public Employee(String[] data) {
		picture = data[0].getBytes();
		id = Integer.parseInt(data[1]);
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
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
}

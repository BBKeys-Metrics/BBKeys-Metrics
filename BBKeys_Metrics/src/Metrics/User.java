package Metrics;


public class User{
	private String username;
	
	/**
	 * Constructor (initialize private variables)
	 */
	public User(String username) {
		this.username = username;
	}
	
	/**
	 * Set the value of the username of the User
	 * @param username - the new value of the username
	 * @return void
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Get the username of the User
	 * @return String
	 */
	public String getUsername() {
		return username;
	}
}

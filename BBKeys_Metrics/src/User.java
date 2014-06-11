
public class User extends Employee{
	private String username;
	private String password;
	
	/**
	 * Constructor (initialize private variables)
	 */
	public User() {
		username = "";
		password = "";
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
	
	/**
	 * Set the password of the User
	 * @param password - new value of the password
	 * @return void
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Get the password of the User
	 * @return String
	 */
	public String getPassword() {
		return password;
	}
}
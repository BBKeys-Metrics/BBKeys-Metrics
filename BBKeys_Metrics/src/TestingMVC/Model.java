package TestingMVC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Metrics.BCrypt;
import Metrics.DatabaseConnection;
import Metrics.Employee;
import Metrics.Leader;
import Metrics.Metric;
import Metrics.MetricScore;

public class Model {
	private static final Model instance = new Model();
	private boolean fakeDatabase = false;
	
	private Model() {
	};
	
	public static Model getInstance() {
		return instance;
	}
	
	public Employee getEmployeeByID(String empID) {
		if (!fakeDatabase) {
			ResultSet r = DatabaseConnection.getInstance().executeQuery("Select Peep_First_Name, Peep_Last_Name FROM Metrics.dbo.People WHERE Peep_ID = '" + empID + "'");
			return ResultSetBuilder.buildEmployee(r, empID);
		} else {
			return new Employee("FakeName", empID, getMetricScores(empID));
		}
	}
	
	public String getEmployeeIDByUsername(String username) {
		if (!fakeDatabase) {
			ResultSet r = DatabaseConnection.getInstance().executeQuery("Select EmployeeID FROM Metrics.dbo.Users WHERE username = '" + username + "'");
			return ResultSetBuilder.buildID(r);
		} else {
			return "FakeID";
		}
	}
	
	public Set<MetricScore> getMetricScores(String id) {
		if (!fakeDatabase) {
			ResultSet r = DatabaseConnection.getInstance().executeQuery("Select metricID, score, date FROM Metrics.dbo.Scores WHERE employeeID = '" + id + "'");
			return ResultSetBuilder.buildMetricScores(r);
		} else {
			Set<MetricScore> set = new HashSet<MetricScore>();
			Set<Metric> metrics = Controller.getInstance().getMetrics();
			for (Metric m : metrics) {
				set.add(new MetricScore(m, Math.random() * 100, Calendar.getInstance()));
				set.add(new MetricScore(m, Math.random() * 100, Calendar.getInstance()));
			}
			return set;
		}
	}
	
	public Set<Preference> getPreferences(Employee employee, String employeeID) {
		if (!fakeDatabase) {
			ResultSet r = DatabaseConnection.getInstance().executeQuery("Select metricID, display from Metrics.dbo.Preferences  where employeeID = '" + employeeID + "'");
			return ResultSetBuilder.buildPreferences(r);
		} else {
			Set<Preference> prefs = new HashSet<Preference>();
			Set<Metric> metrics = Controller.getInstance().getMetrics();
			for (Metric m : metrics)
				prefs.add(new Preference(m, Math.random() > 0.5));
			return prefs;
		}
	}
	
	/**
	 * Gets the number of results to display in the leaderboard
	 * @return ResultSet
	 */
	public int getSettings() {
		if (!fakeDatabase) {
			ResultSet r = DatabaseConnection.getInstance().executeQuery("Select numToShowInLeaderboard from Settings");
			return ResultSetBuilder.buildShowLeaderCount(r);
		} else {
			return 3;
		}
	}
	
	/**
	 * Checks if the username exists
	 * @param username
	 * @return boolean: true if there is a user with the specified username in the database
	 */
	public boolean usernameExists(String username) {
		if (!fakeDatabase) {
			try {
				//connect to the database
				//check that the username exists
				ResultSet r = DatabaseConnection.getInstance().executeQuery("Select COUNT(employeeID) FROM Metrics.dbo.Users WHERE username = '" + username + "'");
				r.next();
				
				if (!r.getString(1).equals("1")) { //if there were no users with the specified username
					return false;
				}
				else { //there is a user with the specified username
					return true;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				return false;
			}
		}
		else {
			return true;
		}
	}
	
	/**
	 * Checks if the username and password combination is a valid login
	 * @param username
	 * @param password
	 * @return boolean: true is the username and password combination is valid
	 */
	public boolean correctLogin(String username, String password) {
		if (!fakeDatabase) {
			try {
				//get the password for the specifiec user
				ResultSet r = DatabaseConnection.getInstance().executeQuery("Select password from Metrics.dbo.Users where username = '" + username + "'");
				r.next();
				String passwordFromDB = r.getString(1);
				
				//Check that an unencrypted password matches one that has previously been hashed
				if (!BCrypt.checkpw(password, passwordFromDB)) { //if the username and password combination were invalid
					//display error message "Invalid Password"
					return false;
				}
				else { //Username and Password combination were valid
					return true;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				return false;
			}
		}
		
		else {
			return true;
		}
	}

	/**
	 * Checks to see if the fields create a valid connection
	 * @param host
	 * @param port
	 * @param database
	 * @param user
	 * @param password
	 * @return boolean: true if the connection is valid
	 */
	public boolean isValidConnection(String host, String port, String database, String user, String password) {
		try {
			//check to see if the required driver is installed
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			//set up connection String
			String connectionURL = "jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + database + ";user=" + user + ";password=" + password;
			
			//create the connection
			try {
				Connection con = DriverManager.getConnection(connectionURL);
				Statement s = con.createStatement();
				s.executeQuery("Select * From Metrics"); //execute a statement to see if it works
				return true;
			} catch (SQLException e) { //the executed statement didn't work
				e.printStackTrace();
				return false;	
			}
		} catch (ClassNotFoundException e) { //necessary driver's are not installed
			e.printStackTrace();
			System.out.println("Driver not installed");
			return false;
		}
	
	}

	/**
	 * Checks that the specified userID is not already used
	 * @param userID
	 * @return boolean: true if the userID has already been used
	 */
	public boolean duplicateIDFound(String userID) {
		if (!fakeDatabase) {
			try {
				ResultSet r = DatabaseConnection.getInstance().executeQuery("SELECT COUNT(employeeID) FROM Metrics.dbo.Users where employeeID = '" + userID + "'");
				r.next();
				
				if (!r.getString(1).equals("0")) { //duplicate id found
					return true;
				}
				else {
					return false;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				return true;
			}
		}
		else {
			return false;
		}
	}

	/**
	 * Checks if the userID is a real userID in the People table
	 * @param userID
	 * @return boolean: true if the userID exists
	 */
	public boolean validID(String userID) {
		if (!fakeDatabase) {
			try {
				ResultSet r = DatabaseConnection.getInstance().executeQuery("SELECT COUNT(Peep_ID) FROM Metrics.dbo.People where Peep_ID = '" + userID + "'");
    			r.next();
        		if (!r.getString(1).equals("1")) {
        			return false;
        		}
        		else {
        			return true;
        		}
    		} catch (SQLException e1) {
				e1.printStackTrace();
				return false;
			}
		}
		else {
			return true;
		}
	}

	/**
	 * Checks if there is already the specified username in the database
	 * @param username
	 * @return boolean: true if duplicate found
	 */
	public boolean duplicateUsername(String username) {
		if (!fakeDatabase) {
			//check database for duplicate username	
			try {
				ResultSet r = DatabaseConnection.getInstance().executeQuery("SELECT COUNT(employeeID) FROM Metrics.dbo.Users where username = '" + username + "'");
    			r.next();
    			if (!r.getString(1).equals("0")) {
    				return true;
    			}
    			else {
    				return false;
    			}
			} catch (SQLException e1) {
				e1.printStackTrace();
				return true;
			}
		}
		else {
			return false;
		}
	}

	/**
	 * This method adds a new user to the database
	 * @param userID
	 * @param username
	 * @param encryptedPassword
	 */
	public void addNewUser(String userID, String username, String encryptedPassword) {
		if (!fakeDatabase) {
			try {
				DatabaseConnection.getInstance().executeUpdate("INSERT INTO Metrics.dbo.Users (employeeID, username, password) VALUES (" + userID + ", '" + username + "', '" + encryptedPassword + "')");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		else {
			
		}
	}

	public Metric getMetricByID(int metricID) {
		if (!fakeDatabase) {
			ResultSet r = DatabaseConnection.getInstance().executeQuery("Select id, name, weight, precision, sorttype FROM Metrics.dbo.Metrics WHERE id = '" + metricID + "'");
			return ResultSetBuilder.buildMetric(r);
		}
		else
			//TODO
			return new Metric("FakeMetric", .5, 2, "ShouldHaveEnum", metricID);
	}

	public Set<Metric> getMetrics() {
		if (!fakeDatabase) {
			//TODO fill this in...
			ResultSet r = DatabaseConnection.getInstance().executeQuery("Select id, name, weight, precision, sorttype from Metrics.DBO.Metrics");
			return ResultSetBuilder.buildMetrics(r);
		} else {
			Set<Metric> metrics = new HashSet<Metric>();
			metrics.add(new Metric("Speed", 0, 0, "Low", 0));
			metrics.add(new Metric("Accuracy", 0, 0, "High", 1));
			metrics.add(new Metric("Helpfulness", 0, 0, "High", 2));
			return metrics;
		}
	}

	public void setUpConnection(String host, String port, String database,
			String username, String password) {
		DatabaseConnection.getInstance().setUpConnection(host, port, database, username, password);
		
	}

	public List<Leader> getTopLeaders(Metric metric, TimeSpan timeUnit) {
		// TODO Auto-generated method stub
		return null;
	}
}

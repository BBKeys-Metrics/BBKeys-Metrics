package TestingMVC;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import Metrics.BCrypt;
import Metrics.DatabaseConnection;
import Metrics.Employee;
import Metrics.Metric;
import Metrics.MetricScore;

public class Model {
	private static final Model instance = new Model();
	private DataSource source;
	private boolean fakeDatabase = false;
	
	private Model() {
	};
	
	public static Model getInstance() {
		return instance;
	}
	
	public ResultSet getEmployeeByID(String empID) {
		if (!fakeDatabase)
			return DatabaseConnection.getInstance().executeQuery("Select Peep_First_Name, Peep_Last_Name FROM Metrics.dbo.People WHERE Peep_ID = '" + empID + "'");
		else
			return null;
	}
	
	public ResultSet getEmployeeIDByName(String name) {
		return DatabaseConnection.getInstance().executeQuery("Select EmployeeID FROM Metrics.dbo.Users WHERE username = '" + name + "'");
	}
	
	public Metric getMetric(int metricID) {
		if (source == null || !source.hasSource()) {
			return null;
		}
		else {
			ResultSet r = null;
			try {
				r = source.executeQuery("Select * FROM Metrics.dbo.Metrics WHERE id = '" + metricID + "'");
				ResultSetMetaData rsmd = r.getMetaData();
				int columns = rsmd.getColumnCount();
				String[] metricData = new String[columns];
				for (int i = 1; i < columns; i++) {
					metricData[i-1] = r.getString(i);
				}
				String name = metricData[2];
				float weight = Float.parseFloat(metricData[3]);
				int precision = Integer.parseInt(metricData[4]);
				String sortType = metricData[5];
				Metric m = new Metric(name, weight, precision, sortType);
				return m;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public Set<MetricScore> getMetricScores(String id) {
		return null;
	}
	
	public Set<Preference> getPreferences(Employee employee) {
		if (source == null || !source.hasSource()) {
			return null;
		}
		else {
			ResultSet r = null;
			try {
				r = source.executeQuery("Select * FROM Metrics.dbo.Preferences WHERE employeeID = '" + employee.getID() + "'");
				Set<Preference> prefs = new HashSet<Preference>();
				while (r.next()) {
					ResultSetMetaData rsmd = r.getMetaData();
					int columns = rsmd.getColumnCount();
					String[] prefData = new String[columns];
					for (int i = 1; i <= columns; i++) {
						prefData[i-1] = r.getString(i);
					}
					Preference pref = new Preference(getMetric(Integer.parseInt(prefData[1])),Boolean.getBoolean(prefData[2]));
					prefs.add(pref);
				}
				return prefs;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public ResultSet getSettings() {
		return DatabaseConnection.getInstance().executeQuery("Select numToShowInLeaderboard from Settings");
	}
	
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
}

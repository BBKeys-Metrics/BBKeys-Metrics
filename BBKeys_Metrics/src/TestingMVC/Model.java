package TestingMVC;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import Metrics.DatabaseConnection;
import Metrics.Employee;
import Metrics.Metric;
import Metrics.MetricScore;

public class Model {
	private static final Model instance = new Model();
	private DataSource source;
	private boolean fakeDatabase = true;
	
	private Model() {
	};
	
	public static Model getInstance() {
		return instance;
	}
	
	public Employee getEmployeeByID(String empID) {
		if (!fakeDatabase)
			return ResultSetBuilder.buildEmployee(DatabaseConnection.getInstance().executeQuery("Select Peep_First_Name, Peep_Last_Name FROM Metrics.dbo.People WHERE Peep_ID = '" + empID + "'"));
		else
			//TODO
			return null;
	}
	
	public String getEmployeeIDByUsername(String username) {
		if (!fakeDatabase) {
			//TODO fill this in...
			ResultSet r = DatabaseConnection.getInstance().executeQuery("Select EmployeeID FROM Metrics.dbo.Users WHERE username = '" + username + "'");
			return ResultSetBuilder.buildID(r);
		}
		else
			return null;
	}
	
	public Metric getMetric(int metricID) {
		if (!fakeDatabase) {
			ResultSet r = DatabaseConnection.getInstance().executeQuery("Select * FROM Metrics.dbo.Metrics WHERE id = '" + metricID + "'");
			return ResultSetBuilder.buildMetric(r);
		}
		else
			//TODO
			return null;
	}
	
	public Set<MetricScore> getMetricScores(String id) {
		//TODO
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
}

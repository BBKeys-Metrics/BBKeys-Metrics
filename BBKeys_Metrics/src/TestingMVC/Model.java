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
	
	private Model() {
	};
	
	public static Model getInstance() {
		return instance;
	}
	
	public ResultSet getEmployeeByID(String empID) {
		/*if (source == null || !source.hasSource()) {
			return null;
		}
		else {
			return source.executeQuery("Select * FROM Metrics.dbo.Employee WHERE id = '" + empID + "'");		
		}*/
		return DatabaseConnection.getInstance().executeQuery("Select Peep_First_Name, Peep_Last_Name FROM Metrics.dbo.People WHERE Peep_ID = '" + empID + "'");
	}
	
	public ResultSet getEmployeeIDByName(String name) {
		/*if (source == null || !source.hasSource()) {
			return null;
		}
		else {
			return source.executeQuery("Select EmployeeID FROM Metrics.dbo.Users WHERE username = '" + name + "'");
		}*/
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
	
	public boolean login(String username, String password) {
		String connectionURL = "jdbc:sqlserver://" + "127.0.0.1" + ":" + 1433 + ";databaseName=" + "Metrics" + ";user=" + username + ";password=" + password;
		source = DatabaseSource.getInstance();
		source.connectTo(connectionURL);
			
		if (source.hasSource()) return true;
		else 
			return false;
	}
}

package TestingMVC;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import Metrics.Employee;
import Metrics.Preference;
import Metrics.Preferences;
import Metrics.User;

public class Model {
	private static final Model instance = new Model();
	private DataSource source;
	private User theUser;
	
	private Model() {
	};
	
	public void setUser(User user) {
		theUser = user;
	}
	
	public static Model getInstance() {
		return instance;
	}
	
	public Employee getEmployee(String user) {
		try {
			return new Employee("1");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Employee getEmployee() {
		if (source == null || !source.hasSource()) return null;
		else {
			ResultSet r = null;
			try {
				r = source.executeQuery("Select * FROM Metrics.dbo.Employee WHERE id = (Select EmployeeID FROM Metrics.dbo.Users WHERE Username = '" + theUser.getUsername() + "')");
				r.next();
				ResultSetMetaData rsmd = r.getMetaData();
				int columns = rsmd.getColumnCount();
				String[] empData = new String[columns];
				for (int i = 1; i <= columns; i++) {
					empData[i-1] = r.getString(i);
				}
				return new Employee("1");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public Metric getMetric(Integer metricID) {
		if (source == null || !source.hasSource()) return null;
		else {
			ResultSet r = null;
			try {
				r = source.executeQuery("Select * FROM Metrics.dbo.Metrics WHERE metricID = '" + metricID + "'");
				ResultSetMetaData rsmd = r.getMetaData();
				int columns = rsmd.getColumnCount();
				String[] prefData = new String[columns];
				for (int i = 1; i < columns; i++);
			}
		}
	}
	
	public Preferences getPreferences(Employee employee) {
		if (source == null || !source.hasSource()) return null;
		else {
			ResultSet r = null;
			try {
				r = source.executeQuery("Select * FROM Metrics.dbo.Preferences WHERE employeeID = '" + employee.getID() + "'");
				Set<Preference> prefs = new HashSet();
				while (r.next()) {
					ResultSetMetaData rsmd = r.getMetaData();
					int columns = rsmd.getColumnCount();
					String[] prefData = new String[columns];
					for (int i = 1; i <= columns; i++) {
						prefData[i-1] = r.getString(i);
					}
					Preference pref = new Preference(getMetric(Integer.decode(prefData[1]),Boolean.getBoolean(prefData[2])));
					prefs.add(pref);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean establishConnection() {
		String connectionURL = "jdbc:sqlserver://" + "SHANE-PC" + ":" + 1433 + ";databaseName=" + "Metrics" + ";user=" + theUser.getUsername() + ";password=" + theUser.getPassword();
		source = DatabaseSource.getInstance();
		source.connectTo(connectionURL);
			
		if (source.hasSource()) return true;
		else 
			return false;
	}
}

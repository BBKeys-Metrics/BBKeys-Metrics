package TestingMVC;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import Metrics.DatabaseConnection;
import Metrics.Preferences;
import Metrics.User;

public class Model {
	private static final Model instance = new Model();
	private DatabaseConnection dbCon;
	
	private Model() {
	};
	
	public static Model getInstance() {
		return instance;
	}
	
	public Preferences getPrefs(String user) {
		if (dbCon == null)
			return null;
		else {
			ResultSet r = null;
			try {
				r = dbCon.executeQuery("Select COUNT(employeeID) FROM Metrics.dbo.Users WHERE username = '" + user + "'");
				r.next();
				int employeeID = Integer.decode(r.getString(1));
				r = dbCon.executeQuery("Select * FROM Metrics.dbo.Preference WHERE employeeID = '" + employeeID + "'");
				r.next();
				ResultSetMetaData rsmd = r.getMetaData();
				int columns = rsmd.getColumnCount();
				String[] prefData = new String[columns];
				for (int i = 1; i <= columns; i++) {
					prefData[i-1] = r.getString(i);
				}
				return new Preferences(prefData);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean establishConnection(User user) {
		try {
			dbCon = new DatabaseConnection("SHANE-PC", "1433", "Metrics", user);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		if (dbCon != null) return true;
		else return false;
	}
}

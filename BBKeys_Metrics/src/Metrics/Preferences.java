package Metrics;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class Preferences {
	private Map<String, Boolean> prefs;
	private DatabaseConnection dbCon ;
	
	public Map<String, Boolean> getPrefs() {
		return prefs;
	}
	
	/**
	 * Constructor (initializes private variables based on the User)
	 * @param user - User that the preferences will be setup for
	 */
	public Preferences(String user) {
		prefs = new HashMap<String, Boolean>();
		try {
			dbCon = new DatabaseConnection();
			ResultSet r = dbCon.executeQuery("SELECT name, display FROM user_preferences WHERE username = '" + user + "'");
			while (r.next()) {
				//add each row to the map
				String metric = r.getString(1);
				boolean display = r.getBoolean(2);
				prefs.put(metric, display);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets the preference for a specific Metric as to whether or not to display the Metric for the current User.
	 * If the key already exists, the old value will be overwritten. If the key does not exist, then a new one will
	 * be inserted.
	 * @param metric - the Metric that will have the display preference changed
	 * @param display - the new preference value as to whether or not to display the Metric for the current User
	 * @return void
	 */
	public void setPreference(Metric metric, Boolean display) {
		prefs.put(metric.getName(), display);
	}
	
	/**
	 * Get the preference value for a given Metric
	 * @param metric - the Metric that the preference value is being searched for
	 * @return Boolean
	 */
	public Boolean getPreference(Metric metric) {
		return prefs.get(metric.getName());
	}
	
	/**
	 * This method prints the preferences (key and value).
	 * It is used for confirming that data was properly entered
	 * and stored.
	 */
	public void printPrefs() {
		for(String key: prefs.keySet()) {
			System.out.println(key + " - " + prefs.get(key));
		}
	}
}

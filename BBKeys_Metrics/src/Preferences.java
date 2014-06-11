import java.util.Map;


public class Preferences {
	private Map<Metric, Boolean> prefs;
	
	/**
	 * Constructor (initializes private variables based on the User)
	 * @param user - User that the preferences will be setup for
	 */
	public Preferences(User user) {
		prefs = null;
	}
	
	/**
	 * Sets the preference for a specific Metric as to whether or not to display the Metric for the current User
	 * @param metric - the Metric that will have the display preference changed
	 * @param display - the new preference value as to whether or not to display the Metric for the current User
	 * @return void
	 */
	public void setPreference(Metric metric, Boolean display) {
		
	}
	
	/**
	 * Get the preference value for a given Metric
	 * @param metric - the Metric that the preference value is being searched for
	 * @return Boolean
	 */
	public Boolean getPreference(Metric metric) {
		return true;
	}
}

package Metrics;

import java.util.Set;

import TestingMVC.Model;
import TestingMVC.Preference;


public class Preferences {
	private Set<Preference> prefs;
	
	public Preferences(Employee user) {
		prefs = Model.getInstance().getPreferences(user);
		
	}
	
	public Preferences(Set<Preference> prefs) {
		this.prefs = prefs;
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
		prefs.add(new Preference(metric, display));
	}
	
	public void setPreference(Preference pref) {
		prefs.add(pref);
	}
	
	/**
	 * Get the preference value for a given Metric
	 * @param metric - the Metric that the preference value is being searched for
	 * @return boolean
	 */
	public boolean getPreference(Metric metric) {
		for (Preference p : prefs) {
			if (p.getMetric().equals(metric)) {
				return p.getDisplay();
			}
		}
		System.out.println("Metric:" + metric.getName() + "not in user preferences.");
		return false;
	}
	
	/**
	 * This method prints the preferences (key and value).
	 * It is used for confirming that data was properly entered
	 * and stored.
	 */
	public void printPrefs() {
		for(Preference p : prefs) {
			System.out.println(p);
		}
	}

	public Set<Preference> getAllPreferences() {
		// TODO Auto-generated method stub
		return prefs;
	}
}

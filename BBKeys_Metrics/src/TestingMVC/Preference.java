package TestingMVC;

import Metrics.Metric;

public class Preference {
	private Metric metric;
	private boolean display;
	
	/**
	 * Constructor
	 * @param metric
	 * @param display
	 */
	public Preference(Metric metric, boolean display) {
		this.metric = metric;
		this.display = display;
	}

	/**
	 * Getter method which returns the metric
	 * @return Metric
	 */
	public Metric getMetric() {
		return metric;
	}
	
	/**
	 * Getter method which returns whether or not the preference should be displayed
	 * @return boolean
	 */
	public boolean getDisplay() {
		return display;
	}
}

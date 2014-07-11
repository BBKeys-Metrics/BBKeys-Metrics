package TestingMVC;

import Metrics.Metric;

public class Preference {
	private Metric metric;
	private boolean display;
	
	public Preference(Metric metric, boolean display) {
		this.metric = metric;
		this.display = display;
	}

	public Metric getMetric() {
		return metric;
	}
	
	public boolean getDisplay() {
		return display;
	}
}

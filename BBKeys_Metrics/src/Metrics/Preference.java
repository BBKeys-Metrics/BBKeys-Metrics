package Metrics;

import TestingMVC.Controller;

public class Preference {
	private Metric metric;
	private boolean display;
	
	public Preference(Metric metric, boolean display) {
		this.metric = metric;
		this.display = display;
	}
	
	public Preference(String[] args) {
		metric = Controller.getInstance().getMetricByID(Integer.decode(args[1]));
		display = Boolean.valueOf(args[2]);
	}

	public Metric getMetric() {
		return metric;
	}
	
	public boolean getDisplay() {
		return display;
	}
}

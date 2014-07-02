package Metrics;

import TestingMVC.Model;

public class Preference {
	private Metric metric;
	private boolean display;
	
	public Preference(Metric metric, boolean display) {
		this.metric = metric;
		this.display = display;
	}
	
	public Preference(String[] args) {
		metric = Model.getInstance().getMetric(Integer.decode(args[1]));
		display = Boolean.valueOf(args[2]);
	}

}

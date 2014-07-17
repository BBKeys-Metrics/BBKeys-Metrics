package Metrics;

import java.util.Calendar;


public class MetricScore {
	private Metric metric;
	private double value;
	private Calendar day;
	
	/**
	 * Constructor (initializes private variables)
	 */
	public MetricScore(Metric metric, double value, Calendar day) {
		this.metric = metric;
		this.value = value;
		this.day = day;
	}
	
	/**
	 * Set the value of the Metric
	 * @param metric - new Metric value of the MetricScore
	 * @return void
	 */
	public void setMetric(Metric metric) {
		this.metric = metric;
	}
	
	/**
	 * Get the Metric of the MetricScore
	 * @return Metric
	 */
	public Metric getMetric() {
		return metric;
	}
	
	/**
	 * Set the value of the MetricScore
	 * @param value - new value for the MetricScore
	 * @return void
	 */
	public void setValue(double value) {
		this.value = value;
	}
	
	/**
	 * Get the value of the MetricScore
	 * @return double
	 */
	public double getValue() {
		return value;
	}
	
	/**
	 * Get the value of the data
	 * @return Calendar
	 */
	public Calendar getDate() {
		return day;
	}
}

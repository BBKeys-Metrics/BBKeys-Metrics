package Metrics;


public class MetricScore {
	private Metric metric;
	private double value;
	
	/**
	 * Constructor (initializes private variables)
	 */
	public MetricScore() {
		metric = null;
		value = 0.0;
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
}

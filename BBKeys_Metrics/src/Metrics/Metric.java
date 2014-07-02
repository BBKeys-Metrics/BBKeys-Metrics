package Metrics;


public class Metric {
	private String name;
	private double weight;
	private int precision;
	private int sortType;
	
	/**
	 * Constructor (initializes private variables)
	 */
	public Metric() {
		name = "";
		weight = 0.0;
		precision = 0;
		sortType = 0;
	}
	
	public Metric(String name, double weight, int precision, int sortType) {
		this.name = name;
		this.weight = weight;
		this.precision = precision;
		this.sortType = sortType;
	}
	
	/**
	 * Set the name of the Metric
	 * @param name - new name of the Metric
	 * @return void
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the name of the Metric
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the weight of the Metric
	 * @param weight - new value of the weight of the Metric
	 * @return void
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	/**
	 * Get the value of the weight of the Metric
	 * @return double
	 */
	public double getWeight() {
		return weight;
	}
	
	/**
	 * Set the precision of the Metric
	 * @param precision - new value of the precision 
	 * @return void
	 */
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	
	/**
	 * Get the precision of the Metric
	 * @return int
	 */
	public int getPrecision() {
		return precision;
	}
	
	/**
	 * Set the value of the sortType
	 * @param sortType - the new value of the sortType
	 * @return void
	 */
	public void setSortType(int sortType) {
		this.sortType = sortType;
	}
	
	/**
	 * Get the value of the sortType
	 * @return String
	 */
	public int getSortType() {
		return sortType;
	}
}

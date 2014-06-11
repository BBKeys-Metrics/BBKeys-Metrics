
public class Metric {
	private String name;
	private double weight;
	private int precision;
	//private ? sortType;
	
	/**
	 * constructor (initializes private variables)
	 */
	public Metric() {
		name = "";
		weight = 0.0;
		precision = 0;
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
}

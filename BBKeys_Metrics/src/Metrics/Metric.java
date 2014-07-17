package Metrics;

/*
 * Why is sortType a string? I think this should be an enum or plain int. - Richard
 * 
 * */
public class Metric {
	private String name;
	private double weight;
	private int precision;
	private String sortType;
	private int id;

	/**
	 * Constructor
	 * @param mName
	 * @param mWeight
	 * @param mPrecision
	 * @param mSortType
	 * @param id
	 */
	public Metric(String mName, double mWeight, int mPrecision, String mSortType, int id){
		name = mName;
		weight = mWeight;
		precision = mPrecision;
		sortType = mSortType;
		this.id = id;
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
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	/**
	 * Get the value of the sortType
	 * @return String
	 */
	public String getSortType() {
		return sortType;
	}

	/**
	 * Gets the value of the id
	 * @return
	 */
	public int getID() {
		return id;
	}
}
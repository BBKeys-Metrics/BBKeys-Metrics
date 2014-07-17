package Metrics;

public class Leader {
	private String name;
	private EmployeePic picture;
	private int rank;
	private MetricScore score;
	
	/**
	 * Constructor
	 * @param name
	 * @param picture
	 * @param rank
	 * @param score
	 */
	public Leader(String name, EmployeePic picture, int rank, MetricScore score) {
		this.name = name;
		this.picture = picture;
		this.rank = rank;
		this.score = score;
	}

	/**
	 * Getter method which returns the name
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter method which returns the picture
	 * @return EmployeePic
	 */
	public EmployeePic getPicture() {
		return picture;
	}

	/**
	 * Getter method which returns the rank
	 * @return int
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * Getter method which returns the score
	 * @return MetricScore
	 */
	public MetricScore getScore() {
		return score;
	}
	
	
}

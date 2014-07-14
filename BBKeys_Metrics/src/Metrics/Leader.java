package Metrics;

public class Leader {
	private String name;
	private EmployeePic picture;
	private int rank;
	private MetricScore score;
	
	public Leader(String name, EmployeePic picture, int rank, MetricScore score) {
		this.name = name;
		this.picture = picture;
		this.rank = rank;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public EmployeePic getPicture() {
		return picture;
	}

	public int getRank() {
		return rank;
	}

	public MetricScore getScore() {
		return score;
	}
	
	
}

package Metrics;

import java.util.Set;


public class GradableItem {
	protected String name;
	protected Set<MetricScore> scores;
	
	/**
	 * Constructor (initializes private variables)
	 */
	public GradableItem() {
		name = "";
		scores = null;
	}
	
	public GradableItem(String name, Set<MetricScore> scores) {
		this.name = name;
		this.scores = scores;
	}
	
	/**
	 * Set the name of the GradableItem
	 * @param name - new name of the GradableItem
	 * @return void
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the name of the GradableItem
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Add a single MetricScore to the private ArrayList of MetricScores
	 * @param score - the MetricScore to be added
	 * @return void
	 */
	public void addScore(MetricScore score) {
		scores.add(score);
	}
	
	/**
	 * Get the MetricScore from the private ArrayList by identifying its location within the List
	 * @param index - location within the ArrayList that the MetricScore is located
	 * @return MetricScore
	 */
	public Set<MetricScore> getScores() {
		return scores;
	}
	
	/**
	 * Get the MetricScore from the private ArrayList by identifying it by its name
	 * @param name - name of the MetricScore to be found
	 * @return MetricScore
	 */
	public MetricScore getScore(Metric type) {
		for (MetricScore m : scores) {
			if (m.getMetric() == type) return m;
		}
		//if the metric score doesn't exist, return null
		return null;
	}
}

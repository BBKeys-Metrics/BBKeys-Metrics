package Metrics;

import java.util.ArrayList;


public class GradableItem {
	private String name;
	private ArrayList<MetricScore> scores;
	
	/**
	 * Constructor (initializes private variables)
	 */
	public GradableItem() {
		name = "";
		scores = null;
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
	public MetricScore getScore(int index) {
		return scores.get(index);
	}
	
	/**
	 * Get the MetricScore from the private ArrayList by identifying it by its name
	 * @param name - name of the MetricScore to be found
	 * @return MetricScore
	 */
	public MetricScore getScore(String name) {
		for (int i = 0; i < scores.size(); i++) {
			if (scores.get(i).getMetric().getName().equals(name)) {
				return scores.get(i);
			}
		}
		
		//if the metric score doesn't exist, return null
		return null;
	}
}

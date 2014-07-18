package Metrics;

import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;

import TestingMVC.TimeSpan;


public class GradableItem {
	protected String name;
	protected Set<MetricScore> scores;
	
	/**
	 * Constructor
	 * @param name
	 * @param scores
	 */
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
	public Set<MetricScore> getScores(TimeSpan span) {
		Set<MetricScore> inTime = new TreeSet<MetricScore>();
		for (MetricScore m : scores) {
			Calendar today = Calendar.getInstance();
			/*if (span == TimeSpan.DAY) {
				today.add(Calendar.DAY_OF_YEAR, -1);
			}
			else if (span == TimeSpan.WEEK) {
				today.add(Calendar.WEEK_OF_YEAR, -1);
			}
			else if (span == TimeSpan.MONTH) {
				today.add(Calendar.MONTH, -1);
			}
			else if (span == TimeSpan.YEAR) {
				today.add(Calendar.YEAR, -1); 
			}
			*/if (true){ // span == TimeSpan.EVER or broken
				today.setTimeInMillis(0); // Year 0
			}
			if (m.getDate().compareTo(today) > 0) {
				inTime.add(m);
			}
		}
		
		return inTime;
	}
	
	/**
	 * Gets the average score based on metric and timespan
	 * @param metric
	 * @param time
	 * @return MetricScore
	 */
	public MetricScore getAverageScore(Metric metric, TimeSpan time) {
		double sum = 0;
		int count = 0;
		Set<MetricScore> scores = getScores(time);
		for (MetricScore m : scores) {
			if (m.equals(metric)) {
				sum += m.getValue();
				count++;
			}
		}
		return new MetricScore(metric, sum / count, Calendar.getInstance());
	}
}

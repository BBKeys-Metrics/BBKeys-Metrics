package TestingMVC;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Metrics.Employee;
import Metrics.EmployeePic;
import Metrics.Leader;
import Metrics.Metric;
import Metrics.MetricScore;

public class ResultSetBuilder {
	
	/**
	 * Builds a single employee. Sets up the employee name, id, and metric scores
	 * @param r
	 * @param id
	 * @return Employee
	 */
	public static Employee buildEmployee(ResultSet r, String id) {
		//"Select Peep_First_Name, Peep_Last_Name FROM Metrics.dbo.People WHERE Peep_ID = '" + empID + "'"
		try {
			r.next();
			ResultSetMetaData rsmd = r.getMetaData();
			int columns = rsmd.getColumnCount();
			String[] empData = new String[columns];
			for (int i = 1; i <= columns; i++) {
				empData[i-1] = r.getString(i);
			}
			String name = empData[0] + " " + empData[1];
			System.out.println(name);
			Set<MetricScore> scores = Model.getInstance().getMetricScores(id);
			return new Employee(name, id, scores);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Gets the employee id based on employee username
	 * @param r
	 * @return String
	 */
	public static String buildID(ResultSet r) {
		//"Select EmployeeID FROM Metrics.dbo.Users WHERE username = '" + username + "'"
		try {
			r.next();
			ResultSetMetaData rsmd = r.getMetaData();
			int columns = rsmd.getColumnCount();
			String[] idData = new String[columns];
			for (int i = 1; i <= columns; i++) {
				idData[i-1] = r.getString(i);
			}
			String id = idData[0];
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Builds a single Metric based on metric id
	 * @param r
	 * @return Metric
	 */
	/*public static Metric buildMetric(ResultSet r) {
		//"Select id, name, weight, precision, sorttype FROM Metrics.dbo.Metrics WHERE id = '" + metricID + "'"
		try {
			r.next();
			Metric m = new Metric(r.getString(2), r.getDouble(3), r.getInt(4), r.getString(5), r.getInt(1));
			return m;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}*/
	
	/**
	 * Builds the Set of preferences for the specified user
	 * @param r
	 * @return Set<Preference>
	 */
	public static Set<Preference> buildPreferences(ResultSet r) {
		//"Select metricID, display from Metrics.dbo.Prefernces"
		Set<Preference> prefs = new HashSet<Preference>();
		try {
			while (r.next()) {
				Preference pref = new Preference(Controller.getInstance().getMetricByID(r.getInt(1)),r.getBoolean(2));
				prefs.add(pref);
			}
			return prefs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Gets the number to show in the leaderboard
	 * @param r
	 * @return int
	 */
	public static int buildShowLeaderCount(ResultSet r) {
		//"Select numToShowInLeaderboard from Settings"
		try {
			r.next();
			return r.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Builds all of the Metrics that are available
	 * @param r
	 * @return Set<Metric>
	 */
	public static Set<Metric> buildMetrics(ResultSet r) {
		//"Select id, name, weight, precision, sorttype from Metrics.DBO.Metrics"
		Set<Metric> metrics = new HashSet<Metric>();
		try {
			while (r.next()) {
				Metric data = new Metric(r.getString(2), r.getDouble(3), r.getInt(4), r.getString(5), r.getInt(1));
				metrics.add(data);
			}
			return metrics;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Builds all of the Metric Scores for the specified employee and returns the Metric Scores as a Set
	 * @param r
	 * @return Set<MetricScore>
	 */
	public static Set<MetricScore> buildMetricScores(ResultSet r) {
		//"Select metricID, score, date FROM Metrics.dbo.Scores WHERE employeeID = '" + id + "'"
		Set<MetricScore> scores = new HashSet<MetricScore>();
		try {
			while (r.next()) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(r.getDate(3));
				MetricScore data = new MetricScore(Controller.getInstance().getMetricByID(Integer.parseInt(r.getString(1))),Double.parseDouble(r.getString(2)), cal);
				scores.add(data);
			}
			return scores;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Builds the list of leaders for a specific metric
	 * @param r
	 * @param metricID
	 * @return List<Leader>
	 */
	public static List<Leader> buildTopLeaders(ResultSet r, int metricID) {
		// Select TOP(" + String.valueOf(Controller.getInstance().getNumToDisplay()) + ") Peep_First_Name, Peep_Last_Name, employeeID, score_avg from Metrics.dbo.people_scores_" + view + "values WHERE metricID = " + String.valueOf(metric.getID()) + " order by score_avg " + sortType
		List<Leader> leaders = new ArrayList<Leader>();
		int rank = 1;
		try {
			while (r.next()) {
				Metric metric = Controller.getInstance().getMetricByID(Integer.valueOf(metricID));
				MetricScore mScore = new MetricScore(metric, Double.valueOf(r.getString(4)), null);
				EmployeePic pic = new EmployeePic(r.getString(3));
				Leader leader = new Leader(r.getString(1) + " "  + r.getString(2), pic, rank, mScore);
				rank++;
				leaders.add(leader);
			}
			return leaders;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

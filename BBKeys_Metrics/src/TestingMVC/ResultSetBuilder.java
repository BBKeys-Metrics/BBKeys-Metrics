package TestingMVC;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import Metrics.Employee;
import Metrics.Metric;
import Metrics.MetricScore;

public class ResultSetBuilder {
	
	
	public static Employee buildEmployee(ResultSet r, String id) {
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
			//Set<MetricScore> scores = Model.getInstance().getMetricScores(id);
			return new Employee(name, id, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String buildID(ResultSet r) {
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
	
	public static Metric buildMetric(ResultSet r) {
		try {
			ResultSetMetaData rsmd = r.getMetaData();
			int columns = rsmd.getColumnCount();
			String[] metricData = new String[columns];
			for (int i = 1; i < columns; i++) {
				metricData[i-1] = r.getString(i);
			}
			int id = Integer.parseInt(metricData[1]);
			String name = metricData[2];
			float weight = Float.parseFloat(metricData[3]);
			int precision = Integer.parseInt(metricData[4]);
			String sortType = metricData[5];
			Metric m = new Metric(name, weight, precision, sortType, id);
			return m;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Set<Preference> buildPreferences(ResultSet r) {
		Set<Preference> prefs = new HashSet<Preference>();
		try {
			while (r.next()) {
				ResultSetMetaData rsmd = r.getMetaData();
				int columns = rsmd.getColumnCount(); 
				String[] prefData = new String[columns];
				for (int i = 1; i <= columns; i++) {
					prefData[i-1] = r.getString(i);
				}
				Preference pref = new Preference(Controller.getInstance().getMetricByID(Integer.parseInt(prefData[0])),Boolean.getBoolean(prefData[1]));
				prefs.add(pref);
			}
			return prefs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
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

	public static Set<MetricScore> buildMetricScores(ResultSet r) {
		//"Select metricID, score, date FROM Metrics.dbo.Scores WHERE employeeID = '" + id + "'"
		Set<MetricScore> scores = new HashSet<MetricScore>();
		try {
			while (r.next()) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(r.getDate(3));
				MetricScore data = new MetricScore(Model.getInstance().getMetricByID(Integer.parseInt(r.getString(1))),Double.parseDouble(r.getString(2)), cal);
				scores.add(data);
			}
			return scores;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

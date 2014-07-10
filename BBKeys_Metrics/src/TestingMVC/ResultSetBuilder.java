package TestingMVC;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import Metrics.Employee;
import Metrics.Metric;
import Metrics.MetricScore;

public class ResultSetBuilder {
	
	public static Employee buildEmployee(ResultSet r) {
		try {
			r.next();
			ResultSetMetaData rsmd = r.getMetaData();
			int columns = rsmd.getColumnCount();
			String[] empData = new String[columns];
			for (int i = 1; i <= columns; i++) {
				empData[i-1] = r.getString(i);
			}
			String name = empData[2];
			String id = empData[1];
			Set<MetricScore> scores = Model.getInstance().getMetricScores(id);
			return new Employee(name, id, scores);
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
				Preference pref = new Preference(Controller.getInstance().getMetricByID(Integer.parseInt(prefData[1])),Boolean.getBoolean(prefData[2]));
				prefs.add(pref);
			}
			return prefs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int buildShowLeaderCount(ResultSet r) {
		//TODO
		return 0;
	}

	public static Set<Metric> buildMetrics(ResultSet r) {
		// TODO Auto-generated method stub
		return null;
	}
}

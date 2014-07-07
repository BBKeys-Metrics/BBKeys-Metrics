package TestingMVC;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Set;

import Metrics.Employee;
import Metrics.EmployeePic;
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
			EmployeePic pic = new EmployeePic(id);
			return new Employee(name, id, scores, pic);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String name = empData[1];
		return new Employee();
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

}

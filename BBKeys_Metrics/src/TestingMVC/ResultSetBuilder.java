package TestingMVC;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import Metrics.Employee;

public class ResultSetBuilder {
	
	public static Employee buildEmployee(ResultSet r) {
		r.next();
		ResultSetMetaData rsmd = r.getMetaData();
		int columns = rsmd.getColumnCount();
		String[] empData = new String[columns];
		for (int i = 1; i <= columns; i++) {
			empData[i-1] = r.getString(i);
		}
		String name = empData[1];
		return new Employee();
	}
	
	public static int buildID(ResultSet r) {
		
		r.next();
		return 0;
	}

}

package TestingMVC;

import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import Metrics.DatabaseConnection;
import Metrics.Employee;
import Metrics.EmployeePic;
import Metrics.Metric;
import Metrics.MetricScore;

public class ResultSetBuilderTest {

  @Test
  public void buildEmployee() {
	ResultSet r = DatabaseConnection.getInstance().executeQuery("Select Peep_First_Name, Peep_Last_Name FROM Metrics.dbo.People WHERE Peep_ID = '1'");
	Employee testEmployee = ResultSetBuilder.buildEmployee(r, "1");
	String name = testEmployee.getName();
	String id = testEmployee.getID();
	EmployeePic pic = testEmployee.getPicture(); 
	
	Assert.assertEquals(name, "Shane Herd");
	Assert.assertEquals(id, "1");
  }

  @Test
  public void buildID() {
	ResultSet r = DatabaseConnection.getInstance().executeQuery("Select EmployeeID FROM Metrics.dbo.Users WHERE username = 'sherd'");
	String id = ResultSetBuilder.buildID(r);
	
	Assert.assertEquals(id, "1");
  }

  @Test
  public void buildMetric() {
	  ResultSet r = DatabaseConnection.getInstance().executeQuery("Select * FROM Metrics.dbo.Metrics WHERE id = '2'");
	  Metric m =  ResultSetBuilder.buildMetric(r);
	  int id = m.getID();
	  String name = m.getName();
	  int precision = m.getPrecision();
	  String sortType = m.getSortType();
	  double weight = m.getWeight();
	  
	  Assert.assertEquals(id, 2);
	  Assert.assertEquals(name, "CreditsCompleted");
	  Assert.assertEquals(precision, 0);
	  Assert.assertEquals(sortType, "4");
	  Assert.assertEquals(weight, 1.0);
  }

  @Test
  public void buildMetricScores() {
	ResultSet r = DatabaseConnection.getInstance().executeQuery("Select metricID, score, date FROM Metrics.dbo.Scores WHERE employeeID = '1'");
	Set<MetricScore> scores = ResultSetBuilder.buildMetricScores(r);
	
	for (MetricScore score : scores) {
		String name = score.getMetric().getName();
		double value = score.getValue();
		Calendar date = score.getDate();
		int id = score.getMetric().getID();
		
		
		System.out.println(id);
		System.out.println(String.valueOf(date.get(Calendar.YEAR)) + "-" + String.valueOf(date.get(Calendar.MONTH) + 1) + "-" + String.valueOf(date.get(Calendar.DAY_OF_MONTH)));
		System.out.println(String.valueOf(value));
		System.out.println();
		
	}
  }

  @Test
  public void buildMetrics() {
	ResultSet r = DatabaseConnection.getInstance().executeQuery("Select id, name, weight, precision, sorttype from Metrics.DBO.Metrics");
	Set<Metric> metrics = ResultSetBuilder.buildMetrics(r);
	
	for (Metric metric : metrics) {
		int id = metric.getID();
		String name = metric.getName();
		int precision = metric.getPrecision();
		String sortType = metric.getSortType();
		double weight = metric.getWeight();
		
		System.out.println(String.valueOf(id));
		System.out.println(name);
		System.out.println(String.valueOf(precision));
		System.out.println(sortType);
		System.out.println(String.valueOf(weight));
		System.out.println();
	}
  }

  @Test
  public void buildPreferences() {
	ResultSet r = DatabaseConnection.getInstance().executeQuery("Select metricID, display from Metrics.dbo.Preferences where employeeID = '1'");
	Set<Preference> prefs = ResultSetBuilder.buildPreferences(r);
	
	System.out.println("buildPreferences");
	
	for (Preference pref : prefs) {
		int id = pref.getMetric().getID();
		boolean display = pref.getDisplay();
		
		System.out.println(String.valueOf(id));
		System.out.println(String.valueOf(display));
		System.out.println();
	}
  }

  @Test
  public void buildShowLeaderCount() {
	ResultSet r = DatabaseConnection.getInstance().executeQuery("Select numToShowInLeaderboard from Settings");
	int count = ResultSetBuilder.buildShowLeaderCount(r);
	
	Assert.assertEquals(count, 4);
  }
}

package TestingMVC;

import java.sql.ResultSet;

import org.testng.Assert;
import org.testng.annotations.Test;

import Metrics.DatabaseConnection;
import Metrics.Employee;
import Metrics.EmployeePic;
import Metrics.Metric;

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
	  Assert.assertEquals(weight, 1);
  }

  @Test
  public void buildMetricScores() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void buildMetrics() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void buildPreferences() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void buildShowLeaderCount() {
    throw new RuntimeException("Test not implemented");
  }
}

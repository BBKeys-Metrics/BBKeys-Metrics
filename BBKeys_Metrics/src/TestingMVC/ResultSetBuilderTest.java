package TestingMVC;

import java.sql.ResultSet;

import org.testng.Assert;
import org.testng.annotations.Test;

import Metrics.DatabaseConnection;
import Metrics.Employee;
import Metrics.EmployeePic;

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
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void buildMetric() {
    throw new RuntimeException("Test not implemented");
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

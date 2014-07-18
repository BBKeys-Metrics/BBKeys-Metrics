package TestingMVC;

import org.testng.annotations.Test;

public class ControllerTest {

  @Test
  public void Controller() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getEmployee() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getEmployeeByName() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getInstance() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getMetricByID() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getMetrics() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getNumToDisplay() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getSettings() {
	  Controller.getInstance().getSettings();
    System.out.println(Controller.getInstance().getNumToDisplay());
  }

  @Test
  public void getTopLeaders() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void initialize() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void setConnectionStrings() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void setUser() {
    throw new RuntimeException("Test not implemented");
  }
}

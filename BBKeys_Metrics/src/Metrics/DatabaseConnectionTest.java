package Metrics;

import java.sql.SQLException;

import org.testng.annotations.Test;

public class DatabaseConnectionTest {

  @Test
  public void DatabaseConnection() {
	  new DatabaseConnection();
  }

  @Test
  public void executeQuery() {
		new DatabaseConnection().executeQuery("Select * From Users");
  }

  @Test
  public void executeQueryAndDisplayResults() {
	  try {
		new DatabaseConnection().executeQueryAndDisplayResults("Select * From Users");;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  @Test
  public void executeQueryWithoutResult() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void executeUpdate() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getConnection() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getInstance() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getResultSet() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void setUpConnection() {
    throw new RuntimeException("Test not implemented");
  }
}

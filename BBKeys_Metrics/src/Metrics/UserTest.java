package Metrics;

import org.testng.annotations.Test;

public class UserTest {

  @Test
  public void getPassword() {
    User user = new User();
    System.out.println(user.getPassword());
  }

  @Test
  public void getUsername() {
    User user = new User();
    System.out.println(user.getUsername());
  }
}

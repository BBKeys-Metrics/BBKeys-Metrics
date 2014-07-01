package Metrics;

import org.testng.annotations.Test;

public class UserTest {

  @Test
  public void getUsername() {
    User user = new User("sherd");
    System.out.println(user.getUsername());
  }
}

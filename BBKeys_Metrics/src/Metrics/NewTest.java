package Metrics;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NewTest {
  @Test
  public void f() {
	  GradableItem gi = new GradableItem();
	  gi.setName("Shane");
	  
	  Assert.assertEquals(gi.getName(), "Shane");
  }
  
  @Test
  public void f2() {
	  GradableItem gi = new GradableItem();
	  gi.setName("Shane");
	  
	  Assert.assertEquals(gi.getName(), "Fail");
  }
}

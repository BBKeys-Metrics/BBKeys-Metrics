package Metrics;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NewTest2 {
  @Test
  public void f() {
	  GradableItem gi = new GradableItem();
	  gi.setName("Shane");
	  
	  Assert.assertEquals(gi.getName(), "Shane");
  }
}

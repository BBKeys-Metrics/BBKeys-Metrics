package Metrics;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GradableItemTest {

  GradableItem gi;
  @Test
  public void GradableItem() {
	  gi = new GradableItem();
  }

  @Test
  public void addScore() {
	  MetricScore ms = new MetricScore();
	  Metric metric = new Metric();
	  metric.setName("Shane");
	  ms.setMetric(metric);
	  gi.addScore(ms);
  }

  @Test
  public void getName() {
	  gi.setName("Shane");
	  Assert.assertEquals(gi.getName(), "Shane");
  }

  @Test
  public void getScoreint() {
	  MetricScore ms = gi.getScore(0);
	  Assert.assertEquals(ms.getMetric().getName(), "Shane");
  }

  @Test
  public void getScoreString() {
	  MetricScore ms = gi.getScore("Shane");
	  Assert.assertEquals(ms.getMetric().getName(), "Shane");
  }

  @Test
  public void setName() {
	  gi.setName("Shane");
	  Assert.assertEquals(gi.getName(), "Shane");
  }
}

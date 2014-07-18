package TestingMVC;

import java.util.List;
import java.util.Set;

import org.testng.annotations.Test;

import Metrics.Leader;
import Metrics.Metric;

public class ModelTest {

  @Test
  public void getTopLeaders() {
	Set<Metric> metrics = Model.getInstance().getMetrics();
	Controller.getInstance().getSettings();
	for (Metric metric : metrics) {
		System.out.println(metric.getName());
		TimeSpan timeUnit = TimeSpan.YEAR;
	    List<Leader> leaders = Model.getInstance().getTopLeaders(metric, timeUnit);
	    for (Leader leader : leaders) {
	    	System.out.println(leader.getName());
	    }
	    System.out.println();
	}
	
  }
}

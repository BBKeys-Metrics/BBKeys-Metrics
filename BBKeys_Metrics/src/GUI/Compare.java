package GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Metrics.Metric;
import Metrics.MetricScore;
import java.util.ArrayList;
import java.util.List;
import TestingMVC.Controller;

/**
 * Project: BBKeys-Metrics
 * File:    Compare 
 *
 * Summary: 
 *   Compare compares the user’s data to the department 
 *   average and the top score in the department. The user 
 *   can view this data in different ways, in order to 
 *   help make it more understandable.  The user can also 
 *   choose to view data averages for the last day, week, 
 *   month, six months, and year, as far as such data is 
 *   available.
 * 
 * @author:
 *   Summer Smith
 */


class Compare extends Frame{
	
	/**
	 * Getter for compare, the primary scene for the 
	 * "Compare" page.
	 * @return Scene
	 */
	@Override 
	public Scene getScene(){
		return this.scene;
	}
	
	/**
	 * Singleton 
	 * @return Compare object instance
	 */
	public Compare getInstance(){
		return this;
	}
	
	/**
	 * Start
	 * @param primaryStage
	 * @throws Exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {		
		primaryStage.setTitle("Compare");
		primaryStage.setScene(scene);
		
		buildPage();
	}
	
	
	/**
	 * Loads elements into the scene
	 */
	@Override
	public void buildPage(){
		BorderPane root = new BorderPane();		
		fillMetricNames();
		
		//Call getView to find out if the view is table or scatterplot
		
		//for(int i = 0; i < metricNames.size(); i++)
		//     Call metricCompare for each metric
		
				
		//root.setTop(this.metric());
		//root.setCenter(this.formatScores());
		root.setBottom(this.navigationBox());
		
		scene = new Scene (root, 600, 600);
	}
	
	
	/**
	 * Takes in a metric, gets the user's score,
	 * the average score, and the high score for that metric
	 * and creates a VBox to display that information in.
	 * 
	 * @param metricName-the name of the metric whose data
	 * will be retrieved, and fromatted for comparison.
	 * @param view- graph or scatterplot
	 * @return VBox
	 */
	private VBox metricCompare(String metricName, String view){
		//Format data according to view
		if(view.equals("plot"))
			//call grid plot formatting
			;
		else 
			return tableView();
		return null;
	
	}
	
	
	/**
	 * Returns the comparative data in table format
	 * @return VBox
	 */
	private VBox tableView(){
		VBox table = new VBox();
		
		//call getData to retrieve data
		
		return table;
	}
	
	/**
	 * Returns the comparative data in scatter plot format
	 * @return VBox
	 */
	private VBox plotView(){
		VBox plot = new VBox();
		
		//call getData to retrieve data
		//http://docs.oracle.com/javafx/2/charts/area-chart.htm
		return plot;
	}

	
}

package GUI;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;

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
	
	public Compare getInstance(){
		return this;
	}
	
	/**
	 * Start
	 * @param primaryStage
	 * @throws Exception
	 */
	public void main(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Compare");
		buildPage();
		primaryStage.setScene(scene);
	}
	
	
	/**
	 * Loads the elements into the page
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
	/* Is never used. Removed - Richard
	private VBox metricCompare(String metricName, String view){
		//Format data according to view
		if(view.equals("plot"))
			//call grid plot formatting
			;
		else 
			return tableView();
		return null;
	
	}
	*/
	
	/**
	 * Returns the comparative data in table format
	 * @return VBox
	 */
	/* Is never used, removed. - Richard
	private VBox tableView(){
		VBox table = new VBox();
		
		//call getData to retrieve data
		
		return table;
	}*/
	
	/**
	 * Returns the comparative data in scatter plot format
	 * @return VBox
	 */
	/* Is never used, removed. - Richard
	private VBox plotView(){
		VBox plot = new VBox();
		
		//call getData to retrieve data
		
		return plot;
	}
	*/
	
<<<<<<< HEAD
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
=======
	/**
	 * Calls to the database to get the names of the 
	 * metrics that will be listed.
	 */
	private void getMetrics(){
		metricNames = new ArrayList<String>();
		//TODO: Add database call to fill array
		metricNames.add("Speed");
		metricNames.add("Accuracy");
	}
	
	/**
	 * 
	 * @param unit-what unit of data (current user, high score, avg) that
	 * needs to be retrieved 
	 */
	/* Is never used, removed. - Richard
	private float getData(String unit){
		//TODO: Change return type to METRIC
		float data = 84;
		return data;
>>>>>>> branch 'master' of https://github.com/BBKeys-Metrics/BBKeys-Metrics
	}
	*/
}

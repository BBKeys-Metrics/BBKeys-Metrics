package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
	 * 
	 */
	private void getView(){
		//should form a drop-down menu with scatterplot and table as the options.
		//Returns whichever is selected by the user.
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
		
		return plot;
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
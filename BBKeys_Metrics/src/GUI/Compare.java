package GUI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import Metrics.*;
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
		
	private static Compare instance = new Compare();
	
	/**
	 * Getter for compare, the primary scene for the 
	 * "Compare" page.
	 * @return Scene
	 */
	@Override 
	public Scene getScene(){
		buildPage();
		return this.scene;
	}
	
	/**
	 * Singleton 
	 * @return Compare object instance
	 */
	public static Compare getInstance(){
		return instance;
	}
	
	/**
     * Constructor
     */
	private Compare() {
		
	}
	
	/**
	 * Loads elements into the scene
	 */
	@Override
	public void buildPage(){
		BorderPane root = new BorderPane();		
		root.setPadding(new Insets(10, 20, 10, 20)); //Formatting
		
		fillMetrics();
		
		//Label for page title
		Label title = new Label("Compare");
		
		//Call getView to find out if the view is table or scatterplot
		//if(view.equals("plot"))
			//root.setCenter(this.plotCompare());
		//else
		root.setTop(title);
		root.setLeft(this.tableCompare());
		root.setBottom(this.navigationBox());
		
		//scene = new Scene (root, 500, 500);
	}
	
	
	/**
	 * Formats a grid to hold all the comparative data
	 * in a readable format.
	 * 
	 * @return GridPane
	 */
	private GridPane tableCompare(){
		//gridPane.add(item, column, row, colspan, rowspan);
		
		GridPane metricTable = new GridPane();
		
		//Make columns uniform width
        for (int i = 0; i < 5; i++){
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(20);
            metricTable.getColumnConstraints().add(column);
        }
        
		//Make labels
		Label metricLabel = new Label("Metric");
		Label myScoreLabel = new Label("My Score");
		Label averageLabel = new Label("Department Average");
		Label topScoreLabel = new Label("Top Score");
		
		//Add all the labels to the grid
		metricTable.add(metricLabel, 0, 0);
		metricTable.add(myScoreLabel, 1, 0);
		metricTable.add(averageLabel, 2, 0);
		metricTable.add(topScoreLabel, 3, 0);
		
		Label metricName;
		
		//Add metric and comparative data
		for(int i = 0; i < metrics.size(); i++){
			//Add the metric name
			metricName = new Label(metrics.get(i).getName());
			metricTable.add(metricName, 0, i+1);
		    
			//Add employee's (current users) score
			MetricScore employeeScore = getEmployeeMetricScore(metrics.get(i), timeUnit.getValue());
			Label empScore = new Label(((Double)(employeeScore.getValue())).toString());
			metricTable.add(empScore, 1, i+1);
			
			//Add average score
			MetricScore averageScore = getAverageScore(metrics.get(i), timeUnit.getValue());
			Label avgScore = new Label(((Double)(averageScore.getValue())).toString());
			metricTable.add(avgScore, 2, i+1);
			
			//Add top score
			MetricScore topScore = getTopScore(metrics.get(i), timeUnit.getValue(), 1);
			Label tpScore = new Label(((Double)(topScore.getValue())).toString());
			metricTable.add(tpScore, 3, i+1);
			
		}
		
		//Add timeUnit drop down
		metricTable.add(timeUnit, 4, 0);
	
		return metricTable;	
	}

	
	/**
	 * Returns the comparative data in scatter plot format
	 * @return VBox
	 
	private VBox plotCompare(){
		VBox plot = new VBox();
		
		//call getData to retrieve data
		//http://docs.oracle.com/javafx/2/charts/area-chart.htm
		return plot;
	}
	*/

	
}

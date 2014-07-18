package GUI;

import java.util.List;
import java.util.Set;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import Metrics.*;
import TestingMVC.Controller;
import TestingMVC.TimeSpan;

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
		
		//Call getView to find out if the view is table or scatterplot
		//if(view.equals("plot"))
			//root.setCenter(this.plotCompare());
		//else
		
		root.setTop(this.topBox());
		root.setLeft(this.tableCompare());
		root.setBottom(this.navigationBox());
		
		//scene = new Scene (root, 500, 500);
	}
	
	 /**
     * Formats the top part of the scene with a title and menu
     * @return VBox
     */
    private VBox topBox(){
        VBox topBox = new VBox(5);
        
        HBox titleBox = new HBox();
        titleBox.setId("border-box");
        titleBox.setAlignment(Pos.CENTER);
        Label title = new Label("Compare");
        title.setId("page-title");
        titleBox.getChildren().add(title);
        
        HBox dropDownBox = new HBox();
        dropDownBox.setAlignment(Pos.CENTER_RIGHT);
        dropDownBox.getChildren().add(timeUnit);
        
        topBox.getChildren().addAll(titleBox, dropDownBox);
        
        return topBox;            
    }
	
	
	/**
	 * Formats a grid to hold all the comparative data
	 * in a readable format.
	 * 
	 * @return GridPane
	 */
	private GridPane tableCompare(){
		Employee user = Controller.getInstance().getEmployee();
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
		
		//Add CSS Identifiers and styling
        metricLabel.setId("chart-label");
        metricLabel.setMinWidth(100);
        myScoreLabel.setId("chart-label");
        averageLabel.setId("chart-label");
        topScoreLabel.setId("chart-label");
		
		//Add all the labels to the grid
		metricTable.add(metricLabel, 0, 0);
		metricTable.add(myScoreLabel, 1, 0);
		metricTable.add(averageLabel, 2, 0);
		metricTable.add(topScoreLabel, 3, 0);
		
		Label metricName;
		Set<Metric> metrics = Controller.getInstance().getMetrics();
		
		//Add metric and comparative data
		int i = 0;
		for(Metric m : metrics){

			Controller cont = Controller.getInstance();
			
			//Get current time unit
			TimeSpan time = convertStringToTimeSpan(timeUnit.getValue());
			
			//Add the metric name
			metricName = new Label(m.getName());
			metricName.setId("data-label");
			metricTable.add(metricName, 0, i+1);
				    
			//Add employee's (current users) score
			MetricScore employeeScore = user.getAverageScore(m, time);
			Label empScore = new Label(((Double)(employeeScore.getValue())).toString());
			empScore.setId("score-display");
			metricTable.add(empScore, 1, i+1);
			
			//Add average score
			//TODO department average (average of all the employees for the given metric for the given time frame)
			MetricScore averageScore = user.getAverageScore(m, time);
			Label avgScore = new Label(((Double)(averageScore.getValue())).toString());
			avgScore.setId("score-display");
			metricTable.add(avgScore, 2, i+1);
			
			//Add top score
			List<Leader> top = cont.getTopLeaders(m, time);
			MetricScore topScore = top.get(0).getScore();
			Label tpScore = new Label(((Double)(topScore.getValue())).toString());
			tpScore.setId("score-display");
			metricTable.add(tpScore, 3, i+1);
			
			i++;
		}
	
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

package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import Metrics.*;
import TestingMVC.Controller;

/*
 * Project: BBKeys-Metrics
 * File:    Leader Board
 *
 * Summary:
 *   Leader board will show the top employees, based on 
 *   their overall scores.  The number of leaders shown 
 *   will be according to preferences set by the 
 *   administrator.  A dropdown menu will be available 
 *   allow the user to view the top employee(s) overall, 
 *   or the top employee(s) for each given metric. Data 
 *   can be viewed for the available time periods.
 * 
 * Author:
 *   Summer Smith
 */


//TODO: Create a listener
// resource: see "setting the behavior"
//http://docs.oracle.com/javafx/2/ui_controls/checkbox.htm

public class LeaderBoard extends Frame{
	private static LeaderBoard instance = new LeaderBoard();
	
	//holds the formatted leader data
	//private ArrayList<VBox> leaders; Not used.
	private ArrayList<CheckBox> metricCheckBoxes;
	private int numTopEmployeesToShow = 5;
	
	/**
	 * Getter for leader board, the primary scene for the 
	 * "Leader Board" page.
	 * @return Scene
	 */
	@Override 
	public Scene getScene(){
		buildPage();
		return this.scene;
	}

	/**
	 * Singleton 
	 * @return LeaderBoard object instance
	 */
	public static LeaderBoard getInstance(){
		return instance;
	}
	
	/**
     * Constructor
     */
	private LeaderBoard() {
		
	}
	
	
	/**
	 * Loads elements into the scene
	 */
	public void buildPage(){
		BorderPane root = new BorderPane();		
		
		metrics = Controller.getInstance().getMetrics();
		
		root.setTop(this.makeCheckBoxes());
		root.setLeft(this.formatLeaders());
		root.setBottom(this.navigationBox());
		
		scene = new Scene (root, 500, 500);
	}
	
	/**
	 * Creates a series of checkboxes, one box for
	 * each metric type.  Formats them into a vbox
	 * for display purposes.
	 * @return VBox
	 */
	private HBox makeCheckBoxes(){
		HBox box = new HBox(10);
		
		//Formatting
		box.setAlignment(Pos.CENTER_RIGHT);
        box.setPadding(new Insets(10, 20, 10, 20));
		
		CheckBox newBox = new CheckBox();
		
		for (Metric m : metrics){
			//Generate a check kbox for each metric type
			newBox.setText(m.getName());
			
			//Default is ALL check boxes are checked
			newBox.setSelected(true);
			
			//Add to list
			metricCheckBoxes.add(newBox);
		}
		
		//Add each checkbox from list to the vbox
        for (CheckBox i : metricCheckBoxes) {
            box.getChildren().add(i);
        }
        
        //Add time unit dropdown
        box.getChildren().add(timeUnit);
        
		return box;
	}
	

	/**
	 * Formats the top x leaders, each leader in their own VBox.
	 * Returns an hbox holding all of the leader sub-boxes.
	 * 
	 * @return GridPane
	 */
	private GridPane formatLeaders(){
		GridPane leadersBox = new GridPane();
		
		for (int i = 1; i < numTopEmployeesToShow; i++){
			//Add each employee in a stack (horizontally)
			leadersBox.add(this.formatLeaderScores(i), 0 , i-1);
		}
		
		return leadersBox;
	
	}
	
	/**
	 * Creates a VBox holding an employees name and his scores.
	 * The scores displayed are those selected in the checkboxes.
	 * 
	 * @param rank -- an integer indigating the ranked position
	 * of the leader for which the vbox is being created.
	 * I.E. 1 indicates the highest ranked employee,
	 * 4 indicates the fourth higheste employee, ect.
	 * @return VBox
	 */
	private GridPane formatLeaderScores(int rank){
		GridPane leaderBox = new GridPane();
		
		//Retrieve employee name
		//Retrieve employee picture
		//??getTopEmployee (see Frame)
		
		//Retrieve overall score
		//Add overall score
		
		for (int i = 0; i < metricCheckBoxes.size(); i++)
		{
			if (metricCheckBoxes.get(i).isSelected()){
				//Use name of checkbox to get metric
		
				//leaderBox.add(this.formatMetric(metric, rank));
			}

		}
	
		return leaderBox;		
	}
	
	/**
	 * Returns am hbox holding the metric name and the score the
	 * employee recieved on that metric.
	 * 
	 * @param metricName
	 * @return HBox
	 */
	private HBox formatMetric(Metric metric, int leaderRank){
		HBox scoreBox = new HBox();
		
		MetricScore topScore = getTopScore(metric, timeUnit.getValue(), leaderRank);
		Label score = new Label(((Double)(topScore.getValue())).toString());
		
		Label name = new Label(metric.getName());
				
		scoreBox.getChildren().addAll(name, score);
		
		return scoreBox;
	}
	
}

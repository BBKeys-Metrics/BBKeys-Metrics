package GUI;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;


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
 *
 *
 *logic for making check boxes
 *for (int i = 0; i < metricNames.size(); i++){
			//Generate a checkbox for each metric type
			newBox.setText(metricNames.get(i));
			//Default is ALL checkboxes are checked
			newBox.setSelected(true);
			metricCheckBoxes.add(newBox);
		}
 *
 *
 */


public class LeaderBoard extends Frame{
	
	//holds the formatted leader data
	private ArrayList<VBox> leaders;
	private ArrayList<CheckBox> metricCheckBoxes;
	
	/**
	 * Getter for leader board, the primary scene for the 
	 * "Leader Board" page.
	 * @return Scene
	 */
	@Override 
	public Scene getScene(){
		return this.scene;
	}
	
	public LeaderBoard getInstance(){
		return this;
	}
	
	
	@Override
	public void start(Stage primaryStage) {
		fillMetricNames();
		
		buildPage();
        primaryStage.setTitle("LeaderBoard");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	/**
	 * Loads the elements into the page
	 */
	@Override
	public void buildPage(){
	        
	    }
	
	
	
	/**
	 * accesses the database to retrieve the leaders for the given metric
	 * 
	 * @param metric
	 */
	private void fillLeadersList(String metric){
		
	}
	
	/**
	 * formats data for each leader
	 * @return VBox
	 */
	private VBox formatLeader(){
		VBox box = new VBox();
		//Get name
		//Get picture
		//Format name and picture appropriately into vbox
		//
		
		return box;
	}

}
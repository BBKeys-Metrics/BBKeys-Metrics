package GUI;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
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
 */


public class LeaderBoard extends Application{
	private Scene scene;
	
	//holds the formatted leader data
	//private ArrayList<VBox> leaders; Not used.
	private ArrayList<CheckBox> metricCheckBoxes;
	private ArrayList<String> metricTypes; //TODO: Change to type Metric OR GradeableItem???
	
	
	@Override
	public void start(Stage primaryStage) {
		fillMetricTypes();
		
		setScene();
        primaryStage.setTitle("LeaderBoard");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	 public void setScene(){
	        
	    }
	
	/**
	 * accesses the database to find out what metrics are available, and 
	 * generates a checkbox for each metric.
	 */
	private void fillMetricTypes(){
		metricTypes = new ArrayList<String>();
		CheckBox newBox = new CheckBox();
		//Replace with DB call
		//Temporary static data
		metricTypes.add("Speed");
		metricTypes.add("Accuracy");
		metricTypes.add("Helpfullness");
		
		for (int i = 0; i < metricTypes.size(); i++){
			//Generate a checkbox for each metric type
			newBox.setText(metricTypes.get(i));
			//Default is ALL checkboxes are checked
			newBox.setSelected(true);
			metricCheckBoxes.add(newBox);
		}
			
	}
	

}
package GUI;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

/*
 * Project: BBKeys-Metrics
 * File:    My Scores
 *
 * Summary:
 *   My Scores shows the logged-in employee’s personal 
 *   statistics.  This is the functioning ‘home’ page, as 
 *   it is the default page that appears after login has 
 *   been completed.  The user stats page will show the 
 *   employee’s name and picture, along with their scores 
 *   on various metrics.  The overall employee rating will 
 *   also be listed.  The user can view their averages for 
 *   the last day, week, month, six months, and year, as 
 *   far as such data is available. 
 *   
 * Author:
 *   Summer Smith
 */

class MyScores{
	private Scene myScores;
	private ArrayList<String> metricNames;
	
	/**
	 * Start 
	 */
	public void main(Stage primaryStage) throws Exception {
		primaryStage.setTitle("My Scores");
		buildPage();
		primaryStage.setScene(myScores);
	}
	
	/**
	 * Getter for myScores, the primary scene for the 
	 * "My Scores" page.
	 * @return Scene
	 */
	public Scene getMyScoresScene(){
		return myScores;
	}
	
	/**
	 * Creates a scene to be loaded into the frame
	 * 
	 * @return Scene
	 */
	private void buildPage(){
		BorderPane root = new BorderPane();		
		getMetrics();
		
		root.setTop(this.employeeInfo());
		root.setCenter(this.formatScores());
		
		myScores = new Scene (root, 600, 600);
	}
	
	/**
	 * Formats employee into a VBox for display
	 * @return VBox
	 */
	private VBox employeeInfo(){
		VBox employeeInfoBox = new VBox();
		
		employeeInfoBox.setSpacing(10);
		employeeInfoBox.setAlignment(Pos.TOP_LEFT);
       
		//TODO: Add call to getEmployeeName()
		Label empName = new Label("John Jingleheimer");
		//TODO: Add call to getEmployeePhoto();
		
		employeeInfoBox.getChildren().addAll(empName);
	        
		return employeeInfoBox;
	}
	
	
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
	 * For each metric in the metricNames array list,
	 * a vbox is created and added into the hbox.
	 * @return HBox
	 */
	private HBox formatScores(){
		HBox formattedScores = new HBox();
		
		for (int i = 0; i < metricNames.size(); i++){
			Label label = new Label(metricNames.get(i));
			formattedScores.getChildren().add(label);
		}			
		
		return formattedScores;
	}
	


	
}

package GUI;


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

class MyScores extends Frame{

	/**
	 * Getter for my scores, the primary scene for the 
	 * "My Scores" page.
	 * @return Scene
	 */
	@Override 
	public Scene getScene(){
		return this.scene;
	}
	
	/**
	 * Singleton 
	 * @return MyScores Object Instance
	 */
	public MyScores getInstance(){
		return this;
	}
	
	/**
	 * Start
	 * @param primaryStage
	 * @throws Exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("My Scores");
		buildPage();
		primaryStage.setScene(scene);
	}
		
	/**
	 * Loads elements into the scene
	 */
	@Override
	public void buildPage(){
		BorderPane root = new BorderPane();		
		fillMetricNames();
		GridPane grid = new GridPane();
		
		//root.setCenter(grid);
		root.setTop(this.employeeInfo());
		root.setCenter(this.formatScores());
		root.setBottom(this.navigationBox());
		
		scene = new Scene (root, 600, 600);
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
		
		employeeInfoBox.getChildren().add(empName);
	        
		return employeeInfoBox;
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
	
	/**
	 * 
	 * @param metricName
	 * @return VBox
	 */
	private VBox formatMetric(String metricName){
		VBox score = new VBox();
		
		
		
		return score;
	}

	
}

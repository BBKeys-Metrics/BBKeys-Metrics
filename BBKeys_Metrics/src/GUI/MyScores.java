package GUI;


import java.util.Set;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import Metrics.*;
import TestingMVC.Controller;
import TestingMVC.TimeSpan;


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

public class MyScores extends Frame{
	private static MyScores instance = new MyScores();

	/**
	 * Getter for my scores, the primary scene for the 
	 * "My Scores" page.
	 * @return Scene
	 */
	@Override 
	public Scene getScene(){ 
		buildPage();
		scene.getStylesheets().add(MyScores.class.getResource("../Metrics.css").toExternalForm());
		return this.scene;
	}
	 
	/**
	 * Singleton 
	 * @return MyScores Object Instance
	 */
	public static MyScores getInstance(){
		return instance;
	}
	
	/**
     * Constructor
     */
	private MyScores() {
		
	}
	
		
	/**
	 * Loads elements into the scene
	 */
	@Override
	public void buildPage(){
		timeUnit.setValue("DAY");
		BorderPane root = new BorderPane();		
		root.setPadding(new Insets(10, 20, 10, 20)); //Formatting
		
		ImageView empPhoto = new ImageView(Controller.getInstance().getEmployee().getPicture().getImage());
				
		//set width of image
        empPhoto.setFitWidth(100);
        
        //keep scaling of image
        empPhoto.setPreserveRatio(true);
        
		root.setTop(this.employeeInfo());
		root.setRight(timeUnit);
        root.setLeft(empPhoto);
		root.setCenter(this.formatScores());
		root.setBottom(this.navigationBox()); 
		
		scene = new Scene (root, 500, 500);
	}
	
	/**
	 * Formats employee into a VBox for display
	 * @return HBox
	 */
	private HBox employeeInfo(){
		HBox employeeInfoBox = new HBox();
		
		//Formatting
		employeeInfoBox.setSpacing(10);
		employeeInfoBox.setAlignment(Pos.BASELINE_CENTER);
		employeeInfoBox.setPadding(new Insets(10, 20, 10, 20));
		 
		//Get employee name and ID
		Label empName = new Label(Controller.getInstance().getEmployee().getName()); //??Controller.getInstance().getEmployee().getName(); //Not sure if controller has an employee or not...
		Label empID = new Label(Controller.getInstance().getEmployee().getID());
		Label idLabel = new Label("Employee ID:");
		
		//Add CSS Identifiers
		empName.setId("employee-name");
		idLabel.setId("data-label");
			
		employeeInfoBox.getChildren().addAll(empName, idLabel, empID);
	        
		return employeeInfoBox;
	}
	
	
	/**
	 * For each metric in the metricNames array list,
	 * a vbox is created and added into the hbox.
	 * @return VBox
	 */
	private VBox formatScores(){
		VBox formattedScoresBox = new VBox(20);
		
		//Format
		formattedScoresBox.setAlignment(Pos.TOP_CENTER);
		formattedScoresBox.setPadding(new Insets(10, 20, 10, 20));
		        
		for (Metric m : Controller.getInstance().getMetrics()){			
			formattedScoresBox.getChildren().add(this.formatMetric(m));
		}			
		
		return formattedScoresBox;
	}
	
	/**
	 * Returns a vbox holding the metric name and the score the
	 * employee recieved on that metric.
	 * @param metricName
	 * @return HBox
	 */
	private HBox formatMetric(Metric metric){
		HBox scoreBox = new HBox();
		
		TimeSpan time = convertStringToTimeSpan(timeUnit.getValue());
		MetricScore employeeScore = Controller.getInstance().getEmployee().getAverageScore(metric, time);
		
		//Make Labels
		Label score = new Label(((Double)(employeeScore.getValue())).toString());
		Label name = new Label(metric.getName());
		
		//Add CSS Identifiers and styling              
        score.setId("score-display");
        score.setMinWidth(50);
        
        name.setId("metric-name-display");
        name.setMinWidth(150);
        
				
		scoreBox.getChildren().addAll(name,score);
		
		return scoreBox;
	}

	
}

package GUI;


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
		BorderPane root = new BorderPane();		
		root.setPadding(new Insets(10, 20, 10, 20)); //Formatting
		
		fillMetrics();
				
		root.setTop(this.employeeInfo());
		root.setLeft(timeUnit);
		root.setCenter(this.formatScores());
		root.setBottom(this.navigationBox()); 
		
		scene = new Scene (root, 500, 500);
	}
	
	/**
	 * Formats employee into a VBox for display
	 * @return VBox
	 */
	private VBox employeeInfo(){
		VBox employeeInfoBox = new VBox();
		
		//Formatting
		employeeInfoBox.setSpacing(10);
		employeeInfoBox.setAlignment(Pos.TOP_LEFT);
		employeeInfoBox.setPadding(new Insets(10, 20, 10, 20));
		 
		//Get employee name and ID
		Label empName = new Label(employee.getName()); //??Controller.getInstance().getEmployee().getName(); //Not sure if controller has an employee or not...
		Label empID = new Label(employee.getID());
		
		
		VBox namesBox = new VBox(5);
        namesBox.getChildren().addAll(empName, empID);
		
		ImageView empPhoto = new ImageView(employee.getPicture().getImage());
	
		employeeInfoBox.getChildren().addAll(empPhoto, namesBox);
	        
		return employeeInfoBox;
	}
	
	
	/**
	 * For each metric in the metricNames array list,
	 * a vbox is created and added into the hbox.
	 * @return HBox
	 */
	private HBox formatScores(){
		HBox formattedScoresBox = new HBox(20);
		
		//Format
		formattedScoresBox.setAlignment(Pos.CENTER);
		formattedScoresBox.setPadding(new Insets(0, 20, 10, 20));
		
		//Add data labels into their own vbox
		Label metricName = new Label("Metric Name:");
        Label metricScore = new Label("Metric Score:");
        
        VBox elementLables = new VBox();
        elementLables.getChildren().addAll(metricName, metricScore);
        
        formattedScoresBox.getChildren().add(elementLables);
        
		for (int i = 0; i < metrics.size(); i++){			
			formattedScoresBox.getChildren().add(this.formatMetric(metrics.get(i)));
		}			
		
		return formattedScoresBox;
	}
	
	/**
	 * Returns a vbox holding the metric name and the score the
	 * employee recieved on that metric.
	 * @param metricName
	 * @return VBox
	 */
	private VBox formatMetric(Metric metric){
		VBox scoreBox = new VBox();
		
		MetricScore employeeScore = getEmployeeMetricScore(metric, timeUnit.getValue());
		Label score = new Label(((Double)(employeeScore.getValue())).toString());
		
		Label name = new Label(metric.getName());
				
		scoreBox.getChildren().addAll(name,score);
		
		return scoreBox;
	}

	
}

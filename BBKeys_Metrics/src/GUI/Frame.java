package GUI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import Metrics.*;

import java.util.ArrayList;

import TestingMVC.View;



/*
 * Project: BBKeys-Metrics
 * File:    Frame
 *
 * Summary:
 *   Frame is the basic framework for the GUI.  It creates the
 *   navigation buttons, and contains other abstract methods
 *   that should be implemented in subsequent 
 *   pages (leader board, my scores, and compare).
 * 
 * Author:
 *   Summer Smith
 *   
 *   
 */


abstract class Frame extends Application{
	//Public variables for use in sub-classes
	public Scene scene;
	public ArrayList<Metric> metrics; //TODO: Change to type Metric OR GradeableItem???
	public Employee employee;
	public ObservableList<String> timeUnits = 
		    FXCollections.observableArrayList(
		            "Day",
		            "Week",
		            "Month",
		            "Six Months",
		            "Year"
		        );
	final ComboBox<String> timeUnit = new ComboBox<String>(timeUnits);
	
	//The three navigation buttons
	private Button myScores = new Button();
	private Button compare = new Button();
	private Button leaderBoard = new Button();
		
    // Abstract methods to be defined in sub-classes
	abstract public Scene getScene();
	abstract public void buildPage();
	
	
	/**
	 * Defines the behaviors for the three navigation
	 * buttons.
	 * 
	 * @return VBox
	 */
	public VBox navigationBox(){
		VBox buttonBox = new VBox();
		
		myScores.setText("My Scores");
		compare.setText("Compare");
		leaderBoard.setText("Leader Board");
		
		myScores.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	MyScores myScores = new MyScores();
            	View.getInstance().setScene(myScores.getScene());
            }
		});
		
		compare.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Compare compare = new Compare();
            	View.getInstance().setScene(compare.getScene());
            }
		});
		
		leaderBoard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	LeaderBoard leaderBoard = new LeaderBoard();
            	View.getInstance().setScene(leaderBoard.getScene());
            }
		});
		
		
		buttonBox.setSpacing(10);
		buttonBox.setAlignment(Pos.CENTER_RIGHT);
		buttonBox.getChildren().addAll(myScores, compare, leaderBoard);
		
		return buttonBox;
	}
	
	/**
	 * accesses the database to find out what metrics are available, places
	 * those items into a list for easy access.
	 */
	public void fillMetrics(){
		metrics = new ArrayList<Metric>();
		
		//Instead of filling manually, I need access to a DB with all of the
		//metrics that should be displayed (as defined in preferences).  
		//As you determine, we can either parse through a list 
		// that exists in another object (if another object has a 
		// list of display metrics) or add each individual metric
		// individually.
		Controller.getAllMetrics();
		
		//Temporary static data --DELETE WHEN ABOVE IS COMPLETED.  Thanks!
		Metric speed = new Metric("Speed", 0, 0, "Low");
		Metric accuracy = new Metric("Accuracy", 0, 0, "High");
		Metric helpfulness = new Metric("Helpfulness", 0, 0, "High");
		
		metrics.add();
		metrics.add(accuracy);
		metrics.add(helpfulness);		
	}
	

	
	/**
	 * Given a metric, the employees score for that metric will
	 * be retrieved and returned.
	 * 
	 * @param metric -- the name of the metric score to be retrieved
	 * @param unit-what unit of data (current user, high score, avg) that
	 * needs to be retrieved 
	 * @return MetricScore
	 */
	public MetricScore getEmployeeMetricScore(Metric metric, String timeUnit){
		//Change geDBEmployeeSccore to whatever you want.
		//  It needs to get score for a given metric, for the current user (employee) that is logged in to the app.
		//  Needs to return the correct average for the given time unit (day, week, month, ect)
		//  and return a MetricScore object
		MetricScore temp = Controller.getDBEmployeeScore();
		return temp;
	}
	
	/**
	 * Given a metric, the average score for that metric will
	 * be retrieved and returned.
	 * 
	 * @param metric -- the name of the metric score to be retrieved
	 * @param unit-what unit of data (current user, high score, avg) that
	 * needs to be retrieved 
	 * @return MetricScore
	 */
	public MetricScore getAverageScore(Metric metric, String timeUnit){
		
		//Change getDBAverage to whatever you want.
		//  It needs to get the top score for a given metric, for the given time unit (day, week, month, ect)
		//  and return a MetricScore object
		MetricScore temp = Controller.getDBAverage();
		return temp;
	}
	
	/**
	 * Given a metric, the average score for that metric will
	 * be retrieved and returned.
	 * 
	 * @param metric -- the name of the metric score to be retrieved
	 * @param unit -- what unit of data (current user, high score, avg) that
	 * needs to be retrieved 
	 * @param rank -- the rank of the top score desired to retrieve, allows
	 * this function to retrieve data for top x employees (as limited by
	 * preferences)
	 * @return MetricScore
	 */
	public MetricScore getTopScore(Metric metric, String timeUnit, int rank){
		
		//Change getTopScoreFromDB to whatever you want.
		//  It needs to get the top score for a given metric, for the given time unit (day, week, month, ect)
		//  for the given rank (i.e. top score, 2nd high schore, 3rd high score...)
		//  Please return a MetricScore Object
		MetricScore temp = Controller.getTopScoreFromDB();
		return temp;
	}
	
	/*public Employee getTopEmployee(String timeUnit, int rank){
		
		//Temporary Data -- should be changed to db call
		try{
		Employee myEmp = new Employee("12345");
		}catch (Exception E){
			
		}
		return myEmp;
	}*/
	
	
	
}

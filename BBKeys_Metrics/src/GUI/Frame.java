package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
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
            	//load My Scores page
            }
		});
		
		compare.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	View.getInstance().setScene(Compare.getInstance().getScene());
            }
		});
		
		leaderBoard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	//load Leader Board page
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
		
		//TODOReplace with DB call
		//Temporary static data
		Metric speed = new Metric("Speed", 0, 0, "Low");
		Metric accuracy = new Metric("Accuracy", 0, 0, "High");
		Metric helpfulness = new Metric("Helpfulness", 0, 0, "High");
		
		metrics.add(speed);
		metrics.add(accuracy);
		metrics.add(helpfulness);		
	}
	
	/**
	 * 
	 * @param unit-what unit of data (current user, high score, avg) that
	 * needs to be retrieved 
	 */
	public float getData(String unit){
		//TODO: Change return type to METRIC
		//Controller.getInstance().getData();
		float data = 84;
		return data;
	}
	
	/**
	 * Given a metric, the employees score for that metric will
	 * be retrieved and returned.
	 * 
	 * @param metric -- the name of the metric score to be retrieved
	 * @return MetricScore
	 */
	public MetricScore getEmployeeMetricScore(Metric metric){
		
		//Temporary Data -- should be changed to db call
		MetricScore temp = new MetricScore(metric, 50);
		return temp;
	}
	
	
}

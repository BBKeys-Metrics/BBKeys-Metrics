package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import Metrics.Metric;
import Metrics.MetricScore;
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
	public ArrayList<Metric> metricNames; //TODO: Change to type Metric OR GradeableItem???
	
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
	public void fillMetricNames(){
		metricNames = new ArrayList<Metric>();
		//Replace with DB call
		//Temporary static data
		Metric speed = new Metric();
		speed.setName("Speed");
		speed.setWeight(0);
		speed.setPrecision(0);
		speed.setSortType("High");
		
		metricNames.setName("Speed");
		metricNames.add("Accuracy");
		metricNames.add("Helpfullness");		
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
	
	
}

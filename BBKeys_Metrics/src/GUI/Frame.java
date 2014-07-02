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
<<<<<<< HEAD

import java.util.ArrayList;

import Metrics.Metrics;
import TestingMVC.View;
=======
>>>>>>> branch 'master' of https://github.com/BBKeys-Metrics/BBKeys-Metrics


/*
 * Project: BBKeys-Metrics
 * File:    Frame
 *
 * Summary:
 *   Frame is the basic framework for the GUI.  It holds the 
 *   navigation buttons and provides a window for the subsequent 
 *   pages (leader board, my scores, and compare) to open in.
 *   It is a parent class from which subsequent user pages inheret. 
 * 
 * Author:
 *   Summer Smith
 *   
 *   
 */


abstract class Frame extends Application{
	public Scene scene;
	public ArrayList<String> metricNames; //TODO: Change to type Metric OR GradeableItem???
	
	private Button myScores = new Button();
	private Button compare = new Button();
	private Button leaderBoard = new Button();
		
	/**
     * Abstract methods to be defined in sub-classes
     */
	abstract public Scene getScene();
	abstract public void buildPage();
	
	
	private VBox buttonBox(){
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
		
		return buttonBox();
	}
	
	/**
	 * accesses the database to find out what metrics are available, and 
	 * generates a checkbox for each metric.
	 */
	public void fillMetricNames(){
		metricNames = new ArrayList<String>();
		CheckBox newBox = new CheckBox();
		//Replace with DB call
		//Temporary static data
		metricNames.add("Speed");
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

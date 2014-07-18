package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.geometry.Insets;

import java.util.Set;

import TestingMVC.TimeSpan;
import TestingMVC.View;
import Metrics.*;


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


abstract class Frame{
	//Public variables for use in sub-classes
	public Scene scene;
	public ObservableList<String> timeUnits = 
		    FXCollections.observableArrayList(
		            "Day",
		            "Week",
		            "Month",
		            "Year",
		            "Ever"
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
	 * @return HBox
	 */
	public HBox navigationBox(){
		HBox buttonBox = new HBox();
		
		//Set button text
		myScores.setText("My Scores");
		compare.setText("Compare");
		leaderBoard.setText("Leader Board");
		
		//Format buttons to same width and add CSS identifiers
        myScores.setMinWidth(100);
        myScores.setId("button");
        leaderBoard.setMinWidth(100);
        leaderBoard.setId("button");
        compare.setMinWidth(100);
        compare.setId("button");
		
        
        //Set actions
		myScores.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	View.getInstance().setScene(MyScores.getInstance().getScene());
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
            	View.getInstance().setScene(LeaderBoard.getInstance().getScene());
            }
		});
		
		//Formatting 
		buttonBox.setSpacing(10);
		buttonBox.setAlignment(Pos.CENTER_RIGHT);
		buttonBox.setPadding(new Insets(0, 20, 10, 20));
		buttonBox.getChildren().addAll(myScores, compare, leaderBoard);
		
		return buttonBox;
	}
	
	public TimeSpan convertStringToTimeSpan(String time) {
		return TimeSpan.valueOf(time); 
	}
	
	
	
}

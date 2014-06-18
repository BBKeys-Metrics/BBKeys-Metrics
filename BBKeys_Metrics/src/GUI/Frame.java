package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.ArrayList;


/*
 * Project: BBKeys-Metrics
 * File:    Frame
 *
 * Summary:
 *   Frame is the basic framework for the GUI.  It holds the 
 *   navigation buttons and provides a window for the subsequent 
 *   pages (leader board, my scores, and compare) to open in.
 * 
 * Author:
 *   Summer Smith
 */


public class Frame extends Application{
	private Scene scene;

	private Button myScores = new Button();
	private Button compare = new Button();
	private Button leaderBoard = new Button();
	
	/**
     * Calls appropriate functions to set up the window.
     * 
     * @param primaryStage 
     */
	@Override
	public void start(Stage primaryStage) throws Exception {
		setScene();
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	/**
	 * Initializes the root pane and calls functions to
	 * ...
	 */
	public void setScene(){
		AnchorPane root= new AnchorPane();
        
        //root.setTop(this.hbox1());
        //root.setCenter(this.hbox2());
		
		AnchorPane.setBottomAnchor(this.buttonBox(), 10.0);
               
        scene = new Scene(root, 800, 800);
	}
	
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
            	//load Compare page
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
	
	
	
}
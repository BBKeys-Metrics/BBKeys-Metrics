package GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Set;

import Metrics.Leader;
import Metrics.Metric;
import TestingMVC.Controller;
import TestingMVC.TimeSpan;

/*
 * Project: BBKeys-Metrics
 * File:    Leader Board
 *
 * Summary:
 *   Leader board will show the top employees, based on 
 *   their overall scores.  The number of leaders shown 
 *   will be according to preferences set by the 
 *   administrator.  A dropdown menu will be available 
 *   allow the user to view the top employee(s) overall, 
 *   or the top employee(s) for each given metric. Data 
 *   can be viewed for the available time periods.
 * 
 * Author:
 *   Summer Smith
 */

public class LeaderBoard extends Frame{
	private static LeaderBoard instance = new LeaderBoard();
	
	//Indicates the current metric selected by the radio boxes
	Metric metric;
	
	
	/**
	 * Getter for leader board, the primary scene for the 
	 * "Leader Board" page.
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
	 * @return LeaderBoard object instance
	 */
	public static LeaderBoard getInstance(){
		return instance;
	}
	
	/**
     * Constructor
     */
	private LeaderBoard() {
		
	}
	
	
	/**
	 * Loads elements into the scene
	 */
	public void buildPage(){
		BorderPane root = new BorderPane();		
				
		root.setTop(this.topBox());
		root.setLeft(this.formatLeaders());
		root.setBottom(this.navigationBox());
		
		scene = new Scene (root, 500, 500);
	}
	
	/**
     * Formats the top part of the scene with a title and menu
     * @return VBox
     */
    private VBox topBox(){
        VBox topBox = new VBox(5);
        
        HBox titleBox = new HBox();
        titleBox.setId("border-box");
        titleBox.setAlignment(Pos.CENTER);
        Label title = new Label("Leader Board");
        title.setId("page-title");
        titleBox.getChildren().add(title);
        
        HBox dropDownBox = new HBox();
        dropDownBox.setAlignment(Pos.CENTER_RIGHT);
        dropDownBox.getChildren().add(timeUnit);
        
        topBox.getChildren().addAll(titleBox, dropDownBox, this.makeRadioButtons());
        
        return topBox;            
    }
	
	/**
	 * Creates a group of radio buttons, one box for
	 * each metric type.  Formats them into an hbox
	 * for display purposes.
	 * @return VBox
	 */
	private HBox makeRadioButtons(){
		HBox box = new HBox(10);
		
		//Formatting
		box.setAlignment(Pos.CENTER_RIGHT);
        box.setPadding(new Insets(10, 20, 10, 20));
		
		int j = 0;
		
		//Group of radio buttons
        final ToggleGroup group = new ToggleGroup();
		
		for (Metric m : Controller.getInstance().getMetrics()){
			//Generate a check kbox for each metric type
			RadioButton newRadio = new RadioButton();
			newRadio.setText(m.getName());
			
			//Default is the first radio is selected
			if (j == 0){
				newRadio.setSelected(true);
				j++;
			}
			
			//Add to group
			newRadio.setToggleGroup(group);
			
			//Add to the vbox
			box.getChildren().add(newRadio);
		}       
               
        //Add listener for radio buttons
        //When a radio button is selected, change the name value of member variable metric to the selected string
        
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov,
                Toggle old_toggle, Toggle new_toggle) {
                    if (group.getSelectedToggle() != null) {
                    	metric.setName(
                    					group.getSelectedToggle().getUserData().toString()
                    					);
                    
                    }
            }
            });
                    
        
        
		return box;
	}
	

	/**
	 * Formats the top x leaders, each leader in their own VBox.
	 * Returns an grid pane holding all of the leader sub-boxes.
	 * 
	 * @return GridPane
	 */
	private GridPane formatLeaders(){
		GridPane leadersBox = new GridPane();
		
		TimeSpan time = convertStringToTimeSpan(timeUnit.getValue());
		
		//Get list of top employees
		List<Leader> topEmployees = Controller.getInstance().getTopLeaders(metric,time);
		
		for (int i = 0; i < topEmployees.size(); i++){
			//Add each employee in a stack (horizontally)	
			leadersBox.add(this.formatLeaderScores(topEmployees.get(i)), 0 , i);
		}
		
		return leadersBox;
	
	}
	
	/**
	 * Creates a VBox holding an employees name and his scores.
	 * The scores displayed are those selected in the checkboxes.
	 * 
	 * @param rank -- an integer indicating the ranked position
	 * of the leader for which the vbox is being created.
	 * I.E. 1 indicates the highest ranked employee,
	 * 4 indicates the fourth higheste employee, ect.
	 * @return HBox
	 */
	private HBox formatLeaderScores(Leader employee){
		HBox leaderBox = new HBox(30);
        leaderBox.setAlignment(Pos.CENTER);
        leaderBox.setId("border-box1");
        leaderBox.setMinWidth(500);
		
		Label empName;
		Label empScore;
		ImageView empPic;
		
		//Get data
		empName = new Label(employee.getName());
		
		empScore = new Label(((Double)(employee.getScore().getValue())).toString());

		empPic = new ImageView(employee.getPicture().getImage());
					
		//Add formatting
		empName.setId("employee-name");
        empName.setMinWidth(250);
        empScore.setId("score-display");
        empScore.setMinWidth(50);
        
        leaderBox.getChildren().addAll(empPic, empName, empScore);
        
		return leaderBox;		
	}
	
	
	
}

package GUI;

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

import Metrics.*;
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


//TODO: Create a listener
// resource: see "setting the behavior"
//http://docs.oracle.com/javafx/2/ui_controls/checkbox.htm

public class LeaderBoard extends Frame{
	private static LeaderBoard instance = new LeaderBoard();
	
	//Indicates the current metric selected by the radio boxes
	Metric metric;
	
	private int numTopEmployeesToShow = 5;
	
	/**
	 * Getter for leader board, the primary scene for the 
	 * "Leader Board" page.
	 * @return Scene
	 */
	@Override 
	public Scene getScene(){
		buildPage();
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
		
		metrics = Controller.getInstance().getMetrics();
		
		root.setTop(this.makeRadioButtons());
		root.setLeft(this.formatLeaders());
		root.setBottom(this.navigationBox());
		
		scene = new Scene (root, 500, 500);
	}
	
	/**
	 * Creates a series of checkboxes, one box for
	 * each metric type.  Formats them into a vbox
	 * for display purposes.
	 * @return VBox
	 */
	private HBox makeRadioButtons(){
		HBox box = new HBox(10);
		
		//Formatting
		box.setAlignment(Pos.CENTER_RIGHT);
        box.setPadding(new Insets(10, 20, 10, 20));
		
		RadioButton newRadio = new RadioButton();
		int j = 0;
		
		//Group of radio buttons
        final ToggleGroup group = new ToggleGroup();
		
		for (Metric m : metrics){
			//Generate a check kbox for each metric type
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
		        
        //Add time unit dropdown
        box.getChildren().add(timeUnit);
        
               
        //Add listener for radio buttons
        //When a radio button is selected, change the name value of member variable metric to the selected string
        /*
        final ToggleGroup group = new ToggleGroup(); 
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov,
                Toggle old_toggle, Toggle new_toggle) {
                    if (group.getSelectedToggle() != null) {
                    	metric.setName(
                    					group.getSelectedToggle().getUserData().toString());
                    
                    }
                    
            }
            );*/
        
        
		return box;
	}
	

	/**
	 * Formats the top x leaders, each leader in their own VBox.
	 * Returns an hbox holding all of the leader sub-boxes.
	 * 
	 * @return GridPane
	 */
	private GridPane formatLeaders(){
		GridPane leadersBox = new GridPane();
		
		for (int i = 1; i < numTopEmployeesToShow; i++){
			//Add each employee in a stack (horizontally)	
			leadersBox.add(this.formatLeaderScores(i), 0 , i-1);
		}
		
		return leadersBox;
	
	}
	
	/**
	 * Creates a VBox holding an employees name and his scores.
	 * The scores displayed are those selected in the checkboxes.
	 * 
	 * @param rank -- an integer indigating the ranked position
	 * of the leader for which the vbox is being created.
	 * I.E. 1 indicates the highest ranked employee,
	 * 4 indicates the fourth higheste employee, ect.
	 * @return VBox
	 */
	private VBox formatLeaderScores(int rank){
		VBox leaderBox = new VBox();
		
		Label empName;
		Label empScore;
		ImageView empPic;
		
		//Get the list of top employees
		TimeSpan time = convertStringToTimeSpan(timeUnit.getValue());
		
		List<Employee> topEmployees = getTopEmployees( time, metric);
		
		for (int i = 0; i < topEmployees.size(); i++){
			empName = new Label(topEmployees.get(i).getName());
			empScore = new Label("1234");
			empPic = new ImageView(topEmployees.get(i).getPicture().getImage());
			
			leaderBox.getChildren().addAll(empPic, empName, empScore);
		}

	
		return leaderBox;		
	}
	
	/**
	 * Returns am hbox holding the metric name and the score the
	 * employee recieved on that metric.
	 * 
	 * @param metricName
	 * @return HBox
	 */
	private HBox formatMetric(Metric metric, int leaderRank){
		HBox scoreBox = new HBox();
		
		MetricScore topScore = getTopScore(timeUnit.getValue(), metric);
		TimeSpan time = convertStringToTimeSpan(timeUnit.getValue());
		
		Label score = new Label(((Double)(topScore.getValue())).toString());
		
		Label name = new Label(metric.getName());
				
		scoreBox.getChildren().addAll(name, score);
		
		return scoreBox;
	}
	
}

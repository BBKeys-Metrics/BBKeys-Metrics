
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Metrics extends Application {
    //variables that will be used in the runnable classes 
	private static Metrics instance;
	private Stage stage;
	private Scene scene;
	private String originalStyle;
	private int originalWidth;
	private int originalHeight;
	
	//getter method which returns the static instance of this class
	public static Metrics getInstance() {
		return instance;
	}
	
	//getter method which returns the primary stage
	public Stage getStage() {
		return stage;
	}
	
	//getter method which returns the scene
	public Scene getScene() {
		return scene;
	}
	
	//getter method which returns the original style that was used
	public String getOriginalStyleOfScene() {
		return originalStyle;
	}
	
	//getter method which returns the original width of the screen
	public int getOriginalWidth() {
		return originalWidth;
	}
	
	//getter method which returns the original height of the screen
	public int getOriginalHeight() {
		return originalHeight;
	}
			
    @Override
    public void start(Stage primaryStage) {
    	//initialize variables to be used by the calling classes
    	instance = this;
        
        //set the title of the window
        primaryStage.setTitle("Metrics");
        
        //create the grid which will hold all of the elements
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        //Allow elements to grow as the window is resized
        //grow horizontally
        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(20);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(25);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(10);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(25);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setPercentWidth(20);
        grid.getColumnConstraints().addAll(column0, column1, column2, column3, column4);
        
        //grow vertically
        RowConstraints rowConstraints0 = new RowConstraints();
        rowConstraints0.setPercentHeight(10);
        RowConstraints rowConstraints1 = new RowConstraints();
        rowConstraints1.setPercentHeight(5);
        RowConstraints rowConstraints2 = new RowConstraints();
        rowConstraints2.setPercentHeight(10);
        RowConstraints rowConstraints3 = new RowConstraints();
        rowConstraints3.setPercentHeight(65);
        RowConstraints rowConstraints4 = new RowConstraints();
        rowConstraints4.setPercentHeight(10);
        grid.getRowConstraints().addAll(rowConstraints0, rowConstraints1, rowConstraints2, rowConstraints3, rowConstraints4);
        
        //set the minimum width and height of the window
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(500);
        
        //create text view which will prompt the user for a runnable item
        Text enterMetric = new Text("Enter Metric");
        enterMetric.setId("enter-runnable"); //used with css
        grid.add(enterMetric, 0, 0, 1, 1);   //column, row, num columns spanned, num rows spanned
        
        //create the text field where the user will enter the runnable item
        TextField userTextField = new TextField();
        userTextField.setPrefWidth(300);
        grid.add(userTextField, 1, 0, 4, 1);
                        
        //set the size of the window
        originalWidth = 500;
        originalHeight = 500;
        scene = new Scene(grid, originalWidth, originalHeight);
        
        //set the style sheets (css) for the scene 
        scene.getStylesheets().add(Metrics.class.getResource("Metrics.css").toExternalForm());
        originalStyle = "-fx-background-image: url(\"background.jpg\")";
        scene.getRoot().setStyle(originalStyle);
        
        //display the window
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //start out with the text field having the focus
        userTextField.requestFocus();
    }
    
    //main method which launches the application
    public static void main(String[] args) {
        launch(args);
    }
    
    //kill all threads when the program is closed
    @Override
    public void stop() {
        System.exit(0);
    }
}
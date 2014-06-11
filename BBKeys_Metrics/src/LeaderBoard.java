import java.util.ArrayList;


public class LeaderBoard {
	private int numToShow;
	private ArrayList<GradableItem> gradables;
	
	/**
	 * Constructor (initializes the private variables)
	 */
	public LeaderBoard() {
		numToShow = 0;
		gradables = null;
	}
	
	/**
	 * Set the number of GradableItems to show
	 * @param numToShow - number of items to show
	 * @return void
	 */
	public void setNumToShow(int numToShow) {
		this.numToShow = numToShow;
	}
	
	/**
	 * Get the number of items to show
	 * @return int
	 */
	public int getNumToShow() {
		return numToShow;
	}
	
	/**
	 * Add a GradableItem to the private ArrayList of GradableItems
	 * @param gradable - GradableItem to be added
	 * @return void
	 */
	public void addGradableItem(GradableItem gradable) {
		
	}
	
	/**
	 * Get the GradableItem from the private ArrayList by identifying its location within the List
	 * @param index - location within the ArrayList that the GradableItem is located
	 * @return GradableItem
	 */
	public GradableItem getGradableItem(int index) {
		GradableItem gradable = new GradableItem();
		
		return gradable;
	}
	
	/**
	 * Get the GradableItem from the private ArrayList by identifying it by its name
	 * @param name - name of the GradableItem to be found
	 * @return GradableItem
	 */
	public GradableItem getGradableItem(String name) {
		GradableItem gradable = new GradableItem();
		
		return gradable;
	}
	
	/**
	 * Displays the LeaderBoard and all of the info of the GradableItems
	 * @return void
	 */
	public void display() {
		
	}
}

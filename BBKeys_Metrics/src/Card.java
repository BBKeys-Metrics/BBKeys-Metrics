
public class Card {
	private GradableItem gradable;
	
	/**
	 * Constructor (initializes the private variables)
	 */
	public Card() {
		gradable = null;
	}
	
	/**
	 * Set the value of the GradableItem
	 * @param gradable - the new value of the GradableItem for the Card
	 * @return void
	 */
	public void setGradable(GradableItem gradable) {
		this.gradable = gradable;
	}
	
	/**
	 * Get the GradableItem
	 * @return GradableItem
	 */
	public GradableItem getGradable() {
		return gradable;
	}
	
	/**
	 * Displays the card and all of the info of the GradableItem
	 * @return void
	 */
	public void display() {
		
	}
}

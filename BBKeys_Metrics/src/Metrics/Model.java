package Metrics;

public class Model {
	private static final Model instance = new Model();
	
	private Model() {
	};
	
	public static Model getInstance() {
		return instance;
	}
}

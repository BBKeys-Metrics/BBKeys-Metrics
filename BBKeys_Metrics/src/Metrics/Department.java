import java.util.ArrayList;


public class Department extends GradableItem{
	private int numEmployees;
	private ArrayList<Employee> employees;
	
	/**
	 * Constructor (initializes private variables)
	 */
	public Department() {
		numEmployees = 0;
		employees = null;
	}
	
	/**
	 * Set the number of employees for the Department
	 * @param numEmployees - new value of numEmployees for Department
	 * @return void
	 */
	public void setNumEmployees(int numEmployees) {
		this.numEmployees = numEmployees;
	}
	
	/**
	 * Get the number of employees for the Department
	 * @return int
	 */
	public int getNumEmployees() {
		return numEmployees;
	}
	
	/**
	 * Add an employee to the ArrayList of Employees
	 * @param employee - new Employee to be added
	 * @return void
	 */
	public void addEmployee(Employee employee) {
		
	}
	
	/**
	 * Get the Employee from the private ArrayList by identifying its location within the List
	 * @param index - location within the ArrayList that the Employee is located
	 * @return Employee
	 */
	public Employee getEmployee(int index) {
		Employee emp = new Employee();
		
		return emp;
	}
	
	/**
	 * Get the Employee from the private ArrayList by identifying it by its name
	 * @param name - name of the Employee to be found
	 * @return Employee
	 */
	public Employee getEmployee(String name) {
		Employee emp = new Employee();
		
		return emp;
	}
}

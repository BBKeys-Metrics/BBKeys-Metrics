package Metrics;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 
 * @author Shane
 *
 */
public class DatabaseConnection {
	private static DatabaseConnection instance = new DatabaseConnection();
	private String host;
	private String port;
	private String database;
	private String username;
	private String password;
	
	private Connection con = null;
	private String connectionURL = null;
	private ResultSet r = null;
	ResultSetMetaData rsmd = null;
	
	/**
	 * Getter method which returns the database connection
	 * @return Connection
	 */
	public Connection getConnection() {
		return con;
	}
	
	private DatabaseConnection() {
		instance = this;
		Properties properties = new Properties();
		try {
			//fill properties with the data from the file
			properties.load(User.class.getResourceAsStream("../databaseConnection.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//set data equal the value of the property named mydata
		host = properties.getProperty("host");
		port = properties.getProperty("port");
		database = properties.getProperty("database");
		username = properties.getProperty("username");
		password = properties.getProperty("password");
		
		setUpConnection();
	}
	
	/**
	 * Sets up the database connection
	 * @return void
	 */
	private void setUpConnection() {
		try {
			//check to see if the required driver is installed
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			//set up connection String
			connectionURL = "jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
			
			//create the connection
			try {
				con = DriverManager.getConnection(connectionURL);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Driver not installed");
		} 
	}
	
	public void setUpConnection(String host, String port, String database, String username, String password) {
		try {
			//check to see if the required driver is installed
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			//set up connection String
			connectionURL = "jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
			
			//create the connection
			try {
				con = DriverManager.getConnection(connectionURL);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Driver not installed");
		} 
	}
	
	/**
	 * Execute the query 
	 * @param query = SQL Query
	 * @return ResultSet
	 */
	public ResultSet executeQuery(String query) {
		Statement s;
		try {
			s = con.createStatement();
			r = s.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * This method executes a query that doesn't return any results such as
	 * a delete statement.
	 * @param query = SQL query
	 * @return void
	 * @throws SQLException
	 */
	public void executeQueryWithoutResult(String query) throws SQLException {
		Statement s = con.createStatement();
		s.executeQuery(query);
	}
	
	/**
	 * This method executes an update query statement such as Update or Insert
	 * @param query
	 * @return void
	 * @throws SQLException
	 */
	public void executeUpdate(String query) throws SQLException {
		Statement s = con.createStatement();
		s.executeUpdate(query);
	}
	
	/**
	 * Execute the query and then display the data from the resulting record set
	 * @param query = SQL Query
	 * @return void
	 */
	public void executeQueryAndDisplayResults(String query) throws SQLException {
		Statement s = con.createStatement();
		r = s.executeQuery(query);
		
		//get the number of columns
		rsmd = r.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		
		//column number starts at 1
		//iterate through each row in the record set
		while (r.next()) {
			//print each column for the current row
			for (int i = 1; i < columnsNumber+1; i++) {
				System.out.print(r.getString(i) + " ");
			}
			
			//print new line
			System.out.println();
		}
	}
	
	/**
	 * Returns the result set from the previous query
	 * @return ResultSet
	 */
	public ResultSet getResultSet() {
		return r;
	}

	public static DatabaseConnection getInstance() {
		return instance;
	}
}

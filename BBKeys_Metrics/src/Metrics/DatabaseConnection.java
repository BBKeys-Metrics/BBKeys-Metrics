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
	private static DatabaseConnection instance;
	private String host;
	private String port;
	private String database;
	private User user;
	
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
	
	public DatabaseConnection() throws SQLException {
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
		user = new User();
		
		setUpConnection();
	}
	
	/**
	 * Constructor which sets the private variables equal to the parameters passed 
	 * @param host - computer host name or IP address
	 * @param port - port that the database is being hosted from
	 * @param database - name of database
	 * @param user - User object
	 */
	/*
	public DatabaseConnection(String host, String port, String database, User user) throws SQLException {
		this.host = host;
		this.port = port;
		this.database = database;
		this.user = user;
		
		setUpConnection();
	}*/
	
	/**
	 * Sets up the database connection
	 * @return void
	 */
	private void setUpConnection() throws SQLException {
		try {
			//check to see if the required driver is installed
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			//set up connection String
			connectionURL = "jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + database + ";user=" + user.getUsername() + ";password=" + user.getPassword();
			
			//create the connection
			con = DriverManager.getConnection(connectionURL);
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
	public ResultSet executeQuery(String query) throws SQLException {
		Statement s = con.createStatement();
		r = s.executeQuery(query);
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

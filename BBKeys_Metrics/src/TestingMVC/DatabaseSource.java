package TestingMVC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseSource implements DataSource {
	private static final DatabaseSource instance = new DatabaseSource();
	private String connectionString;
	private Connection con;
	
	private DatabaseSource() {
	}

	public static DatabaseSource getInstance() {
		return instance;
	}

	@Override
	public boolean connectTo(String dataSource) {
		if (dataSource.equals(connectionString) && con != null) return true;
		connectionString = dataSource;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionString);
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Driver not installed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con = null;
		connectionString = null;
		return false;
	}

	@Override
	public ResultSet executeQuery(String query) {
		try {
			Statement s = con.createStatement();
			ResultSet r = s.executeQuery(query);
			return r;
		} catch (SQLException e) {
			System.out.println("Query Failed.");
		}
		return null;
	}

	@Override
	public boolean hasSource() {
		// TODO Auto-generated method stub
		return con != null;
	}

	@Override
	public void closeSource() {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Failed to close, killing pointer anyway.");
		}
		con = null;
		connectionString = null;
	}

}

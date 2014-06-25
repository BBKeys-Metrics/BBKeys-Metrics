package TestingMVC;

import java.sql.ResultSet;

public interface DataSource {
	
	public boolean connectTo(String dataSource);
	
	public ResultSet executeQuery(String query);
	
	public boolean hasSource();
	
	public void closeSource();
}

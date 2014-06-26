package Metrics;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeePic {
	
	/**
	 * This method reads a varbinary item from a database and writes that as a temporary image file
	 * @param conn = database connection
	 * @param employeeID = employee id for the image desired
	 */
	public void getImageData(Connection conn, String employeeID) {
         byte[] fileBytes = null;
         String query;
         try {
        	 //set up the query to get the varbinary data
             query = "SELECT DocData FROM EmployeePics WHERE EmployeeID = '" + employeeID + "'";
             
             //create query
             Statement state = conn.createStatement();
             
             //execute query
             ResultSet rs = state.executeQuery(query);
             
             //if multiple images are returned, cycle through all of them
             if (rs.next()) {
            	 //get current image
                 fileBytes = rs.getBytes(1);
                 
                 //create new file
                 OutputStream targetFile = new FileOutputStream("temp.JPG");
                 
                 //write the new file
                 targetFile.write(fileBytes);
                 
                 //close the new file
                 targetFile.close();
            }     
         }
         catch (Exception e) {
             e.printStackTrace();
         }
    }
}
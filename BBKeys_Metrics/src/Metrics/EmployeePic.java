package Metrics;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeePic {
	public void getImageData(Connection conn) {
         byte[] fileBytes = null;
         String query;
         
         try {
             query = "SELECT DocData FROM EmployeePics WHERE EmployeeID = '1'";
             Statement state = conn.createStatement();
             ResultSet rs = state.executeQuery(query);
             if (rs.next()) {
                  fileBytes = rs.getBytes(1);
                  OutputStream targetFile = new FileOutputStream("temp.JPG");
                  targetFile.write(fileBytes);
                  targetFile.close();
            }     
         }
         catch (Exception e) {
             e.printStackTrace();
         }
    }
}

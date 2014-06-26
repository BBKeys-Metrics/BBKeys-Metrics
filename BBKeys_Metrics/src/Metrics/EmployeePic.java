package Metrics;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;

public class EmployeePic {
	private byte[] fileBytes;
	BufferedImage bufferedImage;
	Image image;
	
	public EmployeePic() {
		fileBytes = null;
	}
	
	/**
	 * This method reads a varbinary item from a database and writes that as a temporary image file
	 * @param conn = database connection
	 * @param employeeID = employee id for the image desired
	 * @return 
	 */
	public Image getImage(Connection conn, String employeeID) {
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
                 
                 //convert byte array to ByteArrayInputStream
                 ByteArrayInputStream bais = new ByteArrayInputStream(fileBytes);
                 try {
                     bufferedImage = ImageIO.read(bais);
                 } catch (IOException e) {
                	 throw new RuntimeException(e);
                 }
            }     
         }
         catch (Exception e) {
             e.printStackTrace();
         }
         
         return SwingFXUtils.toFXImage(bufferedImage, null);
    }
}
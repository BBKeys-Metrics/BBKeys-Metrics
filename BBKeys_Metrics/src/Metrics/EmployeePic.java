package Metrics;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;

public class EmployeePic {
	private byte[] fileBytes;
	private BufferedImage bufferedImage;
	private Image image;
	private DatabaseConnection dbCon;

	public Image getImage() {
		return image;
	}

	public EmployeePic(String employeeID) {
		dbCon = new DatabaseConnection();
		image = setImage(employeeID);
	}

	/**
	 * This method reads a varbinary item from a database and writes that as a temporary image file
	 * @param employeeID = employee id for the image desired
	 * @return 
	 */
	private Image setImage(String employeeID) {
         String query;
         try {
        	 //set up the query to get the varbinary data
             query = "SELECT DocData FROM EmployeePics WHERE EmployeeID = '" + employeeID + "'";
             
             //create query
             Statement state = dbCon.getConnection().createStatement();
             
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
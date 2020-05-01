package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import app.dao.utils.DatabaseUtils;
import app.model.ProductionCompany;
import app.model.Show;
import app.model.ShowImage;

public class ImageDAO {
	
	public static boolean addNewShowImage(ShowImage imgToAdd) {
		boolean success = true;
		try {
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			
			String sql = String.format("INSERT INTO `show_image_show` (url, show_id) VALUES "
					+ "('%s',%d);", imgToAdd.getUrl(), imgToAdd.getShowID());
			
			statement.executeUpdate(sql);
			
			statement.close();
			DatabaseUtils.closeConnection(connection);
			
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
		}
		
		return success;
		
	}

	public static boolean editShowImage(ShowImage imageToEdit) {
		boolean success = true;

		try {
			String sql = String.format("UPDATE `show_image_show` SET url = '%s'"
					+ "WHERE show_id = %d", imageToEdit.getUrl(), imageToEdit.getShowID());
			
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			int rowCount = statement.executeUpdate(sql);
			if(rowCount == 0){
				success = false;
			}
			
			statement.close();
			DatabaseUtils.closeConnection(connection);
			
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
		}
		
		return success;
	}

}

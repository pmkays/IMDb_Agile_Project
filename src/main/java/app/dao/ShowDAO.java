package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.dao.utils.DatabaseUtils;
import app.model.CreditsRoll;
import app.model.Person;
import app.model.ProductionCompany;
import app.model.Show;
import app.model.ShowImage;
import app.model.UserReview;

public class ShowDAO {

	public static Show getShowByID(String showid) {
		List<Show> shows = new ArrayList<>();

		try {
			String sql = "SELECT * FROM `show`, production_company, show_image_show "
					+ "WHERE `show`.proco_id = production_company.proco_id " 
					+ "AND `show`.show_id = show_image_show.show_id "
					+ "AND `show`.show_id =" + showid;

			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {

				ProductionCompany productionCompany = new ProductionCompany(result.getInt("proco_id"),
						result.getString("proco_name"));

				Show newShow = new Show(result.getInt("show_id"), result.getString("show_title"),
                        result.getDouble("length"), result.getInt("type"),
                        productionCompany, result.getString("genre"), result.getInt("year"),
                        result.getString("synopsis"), result.getInt("status"));


				ShowImage image = new ShowImage(result.getInt("image_id"), result.getString("url"));

				List<ShowImage> images = new ArrayList<ShowImage>();
				images.add(image);
				newShow.setImages(images);
				shows.add(newShow);

			}

			DatabaseUtils.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!shows.isEmpty())
			return shows.get(0);
		return null;
	}

	public static List<Show> getAllShowsByTitleFilter(String filter) {
		List<Show> shows = new ArrayList<>();

		try {
			String sql = "SELECT * FROM `show`, production_company, show_image_show  " + 
						"WHERE upper(show_title) like '%"
						+ filter.toUpperCase() + "%'" + 
						"AND production_company.proco_id = `show`.proco_id " +
						"AND `show`.show_id = show_image_show.show_id";

			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				ProductionCompany productionCompany = new ProductionCompany(result.getInt("proco_id"),
						result.getString("proco_name"));

				Show newShow = new Show(result.getInt("show_id"), result.getString("show_title"),
                        result.getDouble("length"), result.getInt("type"),
                        productionCompany, result.getString("genre"), result.getInt("year"),
                        result.getString("synopsis"), result.getInt("status"));


				ShowImage image = new ShowImage(result.getInt("image_id"), result.getString("url"));
				List<ShowImage> images = new ArrayList<ShowImage>();
				images.add(image);
				newShow.setImages(images);
				
				shows.add(newShow);
			}

			DatabaseUtils.closeConnection(connection);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!shows.isEmpty())
			return shows;
		return null;
	}

	public static List<Show> getAllShowsByPersonFilter(String name) {
		List<Show> shows = new ArrayList<>();

		try {
			String sql = "SELECT distinct * FROM person, credits_roll, `show`, production_company, show_image_show "
					+ "WHERE credits_roll.person_id = person.person_id " + "AND `show`.show_id = credits_roll.show_id "
					+ "AND `show`.show_id = show_image_show.show_id " 
					+ "AND production_company.proco_id = `show`.proco_id " + "AND UPPER(person.fullname) LIKE '%"
					+ name.toUpperCase() + "%' GROUP BY `show`.show_id;";

			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				ProductionCompany productionCompany = new ProductionCompany(result.getInt("proco_id"),
						result.getString("proco_name"));
				
				Show newShow = new Show(result.getInt("show_id"), result.getString("show_title"),
                        result.getDouble("length"), result.getInt("type"),
                        productionCompany, result.getString("genre"), result.getInt("year"),
                        result.getString("synopsis"), result.getInt("status"));
				
				ShowImage image = new ShowImage(result.getInt("image_id"), result.getString("url"));
				List<ShowImage> images = new ArrayList<ShowImage>();
				images.add(image);
				newShow.setImages(images);
				
				shows.add(newShow);
			}
			DatabaseUtils.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return shows;
	}

	public static List<ShowImage> getShowImageByShowId(String showID) {
		List<ShowImage> showImages = new ArrayList<>();

		try {
			String sql = "SELECT * FROM show_image_show WHERE show_id =" + showID;

			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				showImages.add(new ShowImage(result.getInt("image_id"), result.getString("url")));
			}

			DatabaseUtils.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!showImages.isEmpty())
			return showImages;
		return null;
	}

	
	public static int addNewShow(Show showToAdd) {
		int id = -1;

		try {
			String sql = String.format("INSERT INTO `show` (show_title, genre, "
					+ "length, `type`, proco_id, `year`, synopsis, `status`) VALUES "
					+ "('%s','%s',%f,%d,%d,%d,'%s', %d);", 
					showToAdd.getShowTitle(), showToAdd.getGenre(), 
					showToAdd.getLength(), showToAdd.getType(), showToAdd.getProco().getProductID(), 
					showToAdd.getYear(), showToAdd.getSynopsis(), showToAdd.getStatus());
			
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql,  Statement.RETURN_GENERATED_KEYS);
			
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
			
			statement.close();
			DatabaseUtils.closeConnection(connection);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return id;
		
	}
	
	public static boolean editShow(Show showToEdit) {
		boolean success = true;

		try {
			String sql = String.format("UPDATE `show` SET show_title = '%s', "
					+ "genre = '%s',length = %f, `type` = %d, proco_id = %d, "
					+ "`year` = %d, synopsis = '%s', `status`= %d "
					+ "WHERE show_id = %d", 
					showToEdit.getShowTitle(), showToEdit.getGenre(), 
					showToEdit.getLength(), showToEdit.getType(), 
					showToEdit.getProco().getProductID(), 
					showToEdit.getYear(), showToEdit.getSynopsis(), 
					showToEdit.getStatus(), showToEdit.getShowID());
			
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

	// DELETE SHOW METHOD HERE

	public static boolean deleteShow(Show showToDelete)
	{
		boolean success = true;

		try {
			String sql = String.format("DELETE FROM `show` WHERE show_id = %d", showToDelete.getShowID());

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

	public static List<Show> getAllShows()
	{
		List<Show> shows = new ArrayList<>();

		try {
			String sql = "SELECT s.show_id, show_title, genre, length, type, pc.proco_id, proco_name, year, synopsis, status, sis.image_id, url\n" +
						 "FROM imdb.show s\n" +
						 "JOIN production_company pc ON s.proco_id = pc.proco_id\n" +
						 "JOIN show_image_show sis ON s.show_id = sis.show_id;";

			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				ProductionCompany productionCompany = new ProductionCompany(result.getInt("proco_id"),
						result.getString("proco_name"));

				Show newShow = new Show(result.getInt("show_id"), result.getString("show_title"),
						result.getDouble("length"), result.getInt("type"),
						productionCompany, result.getString("genre"), result.getInt("year"),
						result.getString("synopsis"), result.getInt("status"));

				ShowImage image = new ShowImage(result.getInt("image_id"), result.getString("url"));
				List<ShowImage> images = new ArrayList<ShowImage>();
				images.add(image);
				newShow.setImages(images);

				shows.add(newShow);
			}

			DatabaseUtils.closeConnection(connection);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!shows.isEmpty())
			return shows;
		return null;
	}

}

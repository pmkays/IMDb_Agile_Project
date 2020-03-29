package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.dao.utils.DatabaseUtils;
import app.model.CreditsRoll;
import app.model.Show;
import app.model.ShowImage;
import app.model.UserReview;

import javax.xml.transform.Result;


public class ShowDAO {

    public static Show getShowByID(String showid) {
        List<Show> shows = new ArrayList<>();

        try {
            String sql = "SELECT * FROM `show` WHERE show_id =" + showid;

            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while(result.next()) {
                shows.add(
                  new Show(result.getInt("show_id"), result.getString("show_title"), result.getDouble("length"),
                		  result.getBoolean("movie"), result.getBoolean("series"),  result.getInt("proco_id"),
                		  result.getString("genre"), result.getInt("year"), result.getString("synopsis"))
                );
            }

            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        if(!shows.isEmpty()) return shows.get(0);
        return null;
    }
    

	public static List<Show> getAllShowsByTitleFilter(String filter) {
		List<Show> shows = new ArrayList<>();

		try{
		    String sql = "SELECT * FROM `show` WHERE upper(show_title) like '%" + filter.toUpperCase() + "%'";

		    Connection connection = DatabaseUtils.connectToDatabase();
		    Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while(result.next()) {
                shows.add(
                        new Show(result.getInt("show_id"), result.getString("show_title"), result.getDouble("length"),
                                result.getBoolean("movie"), result.getBoolean("series"),  result.getInt("proco_id"),
                                result.getString("genre"), result.getInt("year"), result.getString("synopsis"))
                );
            }

            DatabaseUtils.closeConnection(connection);

        }
		catch (Exception e){
		    e.printStackTrace();
        }

		if(!shows.isEmpty()) return shows;
		return null;
	}
	
	public static List<Show> getAllShowsByPersonFilter(String filter) {
		//TO DO MATT
		return null;
	}
    
	public static ShowImage getShowImageByShowId(String personID) {
		//TO DO
		return null;
	}
	
	public static  List<CreditsRoll> getCreditsRollByShowID(String showID) {
		//TO DO
		return null;
	}
	
	public static UserReview getUserReviewByShowID(String showID) {
		//TO DO
		return null;
	}


}

package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import javax.xml.transform.Result;

public class ShowDAO {

    public static Show getShowByID(String showid) {
        List<Show> shows = new ArrayList<>();

        try {
            String sql = "SELECT * FROM `show` NATURAL JOIN production_company WHERE show_id =" + showid;

            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while(result.next()) {
            	
            	ProductionCompany productionCompany = new ProductionCompany(result.getInt("proco_id"),
            			result.getString("proco_name"));
                shows.add(
                  new Show(result.getInt("show_id"), result.getString("show_title"), result.getDouble("length"),
                		  result.getBoolean("movie"), result.getBoolean("series"), productionCompany,
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
		//TO DO LUKE
		return null;
	}
	
	public static List<Show> getAllShowsByPersonFilter(String filter) {
		//TO DO MATT

        List<Person> actors = new ArrayList<>();

        try
        {
            String sql = "SELECT * FROM 'person' WHERE upper(fullname) like '%' + filter.toUpperCase() + '%'";

            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while(result.next())
            {
                actors.add(
                        new Person(result.getInt("person_id"), result.getString("fullname"), result.getString("role"),
                                result.getDate("birthdate"), result.getString("bio"))
                );
            }
            DatabaseUtils.closeConnection(connection);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // START HERE TOMORROW!
        if(!actors.isEmpty()) return actors;

        return null;
	}
    
	public static List<ShowImage> getShowImageByShowId(String showID) {
		List<ShowImage> showImages = new ArrayList<>();

        try {
            String sql = "SELECT * FROM show_image_show WHERE show_id =" + showID;

            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while(result.next()) {
                showImages.add(
                  new ShowImage(result.getInt("image_id"), result.getString("url"))
                );
            }

            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        if(!showImages.isEmpty()) return showImages;
        return null;
	}
	
	public static  List<CreditsRoll> getCreditsRollByShowID(String showID) {
		List<CreditsRoll> creditsRoll = new ArrayList<>();

        try {
            String sql = "SELECT * FROM credits_roll JOIN person ON credits_roll.person_id = person.person_id "
            		+ "WHERE show_id =" + showID;

            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while(result.next()) {
//            	public Person(int id, String fullName, String role, Date bday, String bio)
            	Person person = new Person(result.getInt("person_id"), result.getString("fullname"),
            			result.getString("role"), result.getDate("birthdate"), result.getString("bio"));
            	
                creditsRoll.add(
                  new CreditsRoll(person, result.getString("role"), result.getInt("start_year"),
                		  result.getString("character_name"), result.getInt("end_year"), result.getInt("show_id"))
                );
            }

            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        if(!creditsRoll.isEmpty()) return creditsRoll;
        return null;
	}
	
	public static List<UserReview> getUserReviewByShowID(String showID) {
		List<UserReview> userReviews = new ArrayList<>();

        try {
            String sql = "SELECT * FROM credits_roll WHERE show_id =" + showID;

            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while(result.next()) {
                userReviews.add(
                  new UserReview(result.getInt("reviewID"), result.getInt("show_id"), result.getString("user_id"),
                		  result.getInt("rating"), result.getString("review"), result.getDate("date"))
                );
            }

            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        if(!userReviews.isEmpty()) return userReviews;
        return null;
		
	}


}

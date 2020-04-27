package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.dao.utils.DatabaseUtils;
import app.model.UserReview;



public class UserReviewDAO {

    public static UserReview getReviewByID(String reviewID) {
        // Fish out the results
        List<UserReview> userReviews = new ArrayList<>();

        try {
            // Here you prepare your sql statement
            String sql = "SELECT * FROM user_review WHERE reviewId =" + reviewID;

            // Execute the query
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // If you have multiple results, you do a while
            while(result.next()) {
                // 2) Add it to the list we have prepared
            	int reviewID1 = result.getInt("reviewId");
        		int showID = result.getInt("show_id");
        		int userID = result.getInt("user_id");
        		int rating = result.getInt("rating");
        		String review = result.getString("review");
        		Date date = result.getDate("date");
        		
                userReviews.add(
                  new UserReview(reviewID1, showID, userID, rating, review, date)
                );
            }

            // Close it
            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        // If there is a result
        if(!userReviews.isEmpty()) return userReviews.get(0);
        // If we are here, something bad happened
        return null;
    }
    //INSERT into user_review(show_id, user_id, rating, title, review, `date`) values (1,'caramel6',3,'wow','this movie suxx', curdate());
    public static void insertReview(String showID, String userID, int rating, String title, String review) {

        try {
            // Here you prepare your sql statement
            String sql = "INSERT into user_review"
            		+ "(show_id, user_id, rating, title, review, `date`) "
            		+ "values (" + showID + ",'"+ userID +"',"+ rating +",'"+ title
            		+"','"+ review +"', curdate());";

            // Execute the query
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

            // Close it
            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public static double getShowAverageRating(String showID) {
    	//SELECT AVG(rating) FROM imdb.user_review;
    	double rating = 0.0;
        try {
            // Here you prepare your sql statement
            String sql = "SELECT AVG(rating) as average FROM imdb.user_review WHERE show_id='"+showID+"';";

            // Execute the query
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            result.next();
            
            rating = result.getDouble("average");

            // Close it
            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return rating;
    }



}

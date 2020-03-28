package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.dao.utils.DatabaseUtils;
import app.model.Show;
import app.model.UserReview;



public class UserReviewDAO {
    public static final String SALT = "$2a$10$h.dl5J86rGH7I8bD9bZeZe";


    /**
     * Method to fetch users from the database.
     * You should use this as an example for future queries, though the sql statement
     * will change -and you are supposed to write them.
     *
     * Current user: caramel 6, password (the password is "password" without quotes)
     * @param username what the user typed in the log in form.
     * @return Some of the user data to check on the password. Null if there
     *         no matching user.
     */
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
                userReviews.add(
                  // 1) Create a new account object                			
                  new UserReview(result.getString("review"), result.getInt("rating"))
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



}

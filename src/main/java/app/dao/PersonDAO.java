package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import app.dao.utils.DatabaseUtils;
import app.model.Person;

public class PersonDAO {
	
	public static Person getPersonById(String id) {
        // Fish out the results
		Person person = null;
        try {
            // Here you prepare your sql statement
            String sql = "SELECT * FROM person WHERE person_id ='" + id + "'";

            // Execute the query
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            result.next();
            //do some check first
            int personId = result.getInt("person_id");
            String fullname = result.getString("fullname");
            String role = result.getString("role");
            Date birthdate = result.getDate("birthdate");
            String bio = result.getString("bio");
            
            person = new Person(personId, fullname, role, birthdate, bio);

            // Close it
            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // If there is a result
        if(person != null){
        	return person;
        }
        // If we are here, something bad happened
        return null;
    }

}

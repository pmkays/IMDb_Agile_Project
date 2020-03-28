package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import app.dao.utils.DatabaseUtils;
import app.model.ActorImage;
import app.model.CreditsRoll;
import app.model.Person;

public class PersonDAO {
	
	public static Person getPersonById(String id) {
		Person person = null;
        try {
            String sql = "SELECT * FROM person WHERE person_id ='" + id + "'";

            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            result.next();

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

        if(person != null){
        	return person;
        }
        return null;
    }
	
	public static List<Person> getAllPersonsByNameFilter(String filter) {
		//TO DO. Low priority.
		return null;
	}
	
	public static ActorImage getPersonImageByPersonId(int personID) {
		//TO DO
		return null;
	}
	
	public static List<CreditsRoll> getCreditsRollByPersonId(int personID) {
		//TO DO
		return null;
	}

}

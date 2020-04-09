package app.dao;

import app.dao.utils.DatabaseUtils;
import app.model.ActorImage;
import app.model.CreditsRoll;
import app.model.Person;
import app.model.Show;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	public static List<Person> getAllPersonsByNameFilter(String name)
    {
        List<Person> actors = new ArrayList<>();

        try
        {
            String sql = "SELECT * FROM 'person' WHERE upper(fullname) like '%' + name.toUpperCase() + '%'";

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

        if(actors != null)
        {
            return actors;
        }
		return null;
	}
	
	public static ActorImage getPersonImageByPersonId(int personID) {
		//TO DO
		return null;
	}
	
	public static List<CreditsRoll> getCreditsRollByPerson(Person person) {
		List<CreditsRoll> creditRolls = new ArrayList();
		CreditsRoll creditRoll = null;
        try {
            String sql = "SELECT * FROM credits_roll WHERE person_id ='" + 
            				person.getPersonId() + "'";

            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()){       	
	            String role = result.getString("role");
	            int showId = result.getInt("show_id");
	            int startYear = result.getInt("start_year");
	            int endYear = result.getInt("end_year");
	            String character = result.getString("character_name");
	            
	            //use retrieved showID to get show 
	            Show show = ShowDAO.getShowByID(Integer.toString(showId));
	            
	            creditRoll = new CreditsRoll(person, role, startYear,
	            		character, endYear, show);
	            
	            creditRolls.add(creditRoll);
            }
            // Close it
            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if(!creditRolls.isEmpty()){
        	return creditRolls;
        }
        return null;
	}

}

package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.dao.utils.DatabaseUtils;
import app.model.CreditsRoll;
import app.model.Person;

public class CreditsRollDAO {
	
	public static List<CreditsRoll> getCreditsRollByShowID(String showID) {
		List<CreditsRoll> creditsRoll = new ArrayList<>();

		try {
			String sql = "SELECT * FROM credits_roll JOIN person ON credits_roll.person_id = person.person_id "
					+ "WHERE show_id =" + showID;

			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				Person person = new Person(result.getInt("person_id"), result.getString("fullname"),
						result.getString("role"), result.getDate("birthdate"), result.getString("bio"));

				creditsRoll.add(new CreditsRoll(person, result.getString("role"), result.getInt("start_year"),
						result.getString("character_name"), result.getInt("end_year"), result.getInt("show_id")));
			}

			DatabaseUtils.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!creditsRoll.isEmpty())
			return creditsRoll;
		return null;
	}
	
	public static boolean addNewCreditRolls(List<CreditsRoll> creditRolls) {
		boolean success = true;
		try {
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			
			for(CreditsRoll crToAdd : creditRolls) {
				String sql = String.format("INSERT INTO `credits_roll` (person_id, role, "
						+ "show_id, start_year, end_year, character_name) VALUES "
						+ "(%d,'%s',%d,%d,%d,'%s');", 
						crToAdd.getPersonID(), crToAdd.getRole(), crToAdd.getShowID(), 
						crToAdd.getStartYear(), crToAdd.getEndYear(), crToAdd.getCharacter());
				

				statement.executeUpdate(sql);
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

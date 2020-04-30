package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import app.dao.utils.DatabaseUtils;
import app.model.CreditsRoll;

public class CreditsRollDAO {

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

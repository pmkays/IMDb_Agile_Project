package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.dao.utils.DatabaseUtils;
import app.model.Account;
import app.model.Show;



public class AccountDAO {
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
    public static Account getUserByUsername(String username) {
        // Fish out the results
        List<Account> accounts = new ArrayList<>();

        try {
            // Here you prepare your sql statement
            String sql = "SELECT * FROM account WHERE username ='" + username + "'";

            // Execute the query
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // If you have multiple results, you do a while
            while(result.next()) {
                // 2) Add it to the list we have prepared
            	accounts.add(
                        // 1) Create a new account object
                      		new Account(
                      				result.getString("username"),
                      				result.getString("password"),
                      				result.getString("first_name"),
                      				result.getString("last_name"),
                      				result.getString("country"),
                      				result.getString("gender"),
                      				result.getString("email"),
                      				result.getString("role"),
                      				result.getString("post_code"),
                      				result.getString("year"),
                      				result.getString("organisation_name"),
                      				result.getString("organisation_number"),
                      				result.getInt("status"),
                      				result.getString("proco_id"))
                      				
                      );
            }

            // Close it
            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        // If there is a result
        if(!accounts.isEmpty()) return accounts.get(0);
        // If we are here, something bad happened
        return null;
    }
    
    public static boolean addNewAccount(Account acct) {
		boolean success = true;

		try {
			String sql;

			if(acct.getProcoId() == null) {
				sql = String.format("INSERT INTO `account` (`username`, `password`, "
						+ "email, country, gender, first_name, last_name, role, "
						+ "post_code, organisation_name, organisation_number, `year`, `status`) VALUES "
						+ "('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s',%d);", 
						acct.getUsername(), acct.getPassword(), 
						acct.getEmail(), acct.getCountry(), acct.getGender(), 
						acct.getFirstName(), acct.getLastName(), acct.getRole(), 
						acct.getPostCode(), acct.getOrganisationName(), 
						acct.getOrganisationNumber(), acct.getYear(), acct.getStatus());
			} else {
				sql = String.format("INSERT INTO `account` (`username`, `password`, "
						+ "email, country, gender, first_name, last_name, role, "
						+ "post_code, organisation_name, organisation_number, `year`, `status`, proco_id) VALUES "
						+ "('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s',%d, %s);",
						acct.getUsername(), acct.getPassword(), 
						acct.getEmail(), acct.getCountry(), acct.getGender(), 
						acct.getFirstName(), acct.getLastName(), acct.getRole(), 
						acct.getPostCode(), acct.getOrganisationName(), 
						acct.getOrganisationNumber(), acct.getYear(), acct.getStatus(), acct.getProcoId());
			}
			
			
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			
			
			statement.close();
			DatabaseUtils.closeConnection(connection);
			
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
		}
		
		return success;
		
	}

	public static List<Account> getPendingAccounts() {
		// Fish out the results
        List<Account> accounts = new ArrayList<>();

        try {
            // Here you prepare your sql statement
            String sql = "SELECT * FROM account WHERE status = 0";

            // Execute the query
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // If you have multiple results, you do a while
            while(result.next()) {
                // 2) Add it to the list we have prepared
            	accounts.add(
                        // 1) Create a new account object
                      		new Account(
                      				result.getString("username"),
                      				result.getString("password"),
                      				result.getString("first_name"),
                      				result.getString("last_name"),
                      				result.getString("country"),
                      				result.getString("gender"),
                      				result.getString("email"),
                      				result.getString("role"),
                      				result.getString("post_code"),
                      				result.getString("year"),
                      				result.getString("organisation_name"),
                      				result.getString("organisation_number"),
                      				result.getInt("status"),
                      				result.getString("proco_id"))
                      );
            }

            // Close it
            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
	}
	
	public static void updateAccountStatus(String username, String status) {
	       try {
	            String sql = String.format("UPDATE account " + 
	            		"SET status = " + status + 
	            		" where username = '"+ username +"'");

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

}

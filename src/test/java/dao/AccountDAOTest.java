package dao;

import app.dao.AccountDAO;
import app.dao.utils.DatabaseUtils;
import app.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;

import static app.dao.AccountDAO.addNewAccount;
import static org.junit.jupiter.api.Assertions.*;

class AccountDAOTest {

	@BeforeEach
	void setUp() {
		Connection connection = null;
		String sql = "DELETE FROM account" + " WHERE username = 'cool_abed_films' OR username = 'shaunaMalwayTweep';";
		try {
			connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		DatabaseUtils.closeConnection(connection);
	}

	@Test
	void addNewAccount_successTrue_validPCoAccount() {
		Account pcoAccount = new Account("cool_abed_films", "GreenDale33", "Abed", "Nadir", "Poland", "Male",
				"coolabedfilms@gmail.com", "pco", "4177", "2020", "Warner Bros.", "2", 2, "4");
		assertTrue(addNewAccount(pcoAccount));
	}

	@Test
	void addNewAccount_successTrue_validCriticAccount() {
		Account criticAccount = new Account("shaunaMalwayTweep", "pTimes78", "Shauna", "Malway-Tweep", "USA", "Female",
				"shauna@pawneejournal.com", "critic", "3106", "2012", "Pawnee Journal", "3", 2);
		assertTrue(addNewAccount(criticAccount));
	}

	@Test
	void addNewAccount_successFalse_invalidAccount() {
		Account nullAccount = null;
		assertFalse(addNewAccount(nullAccount));
	}


	@Test
	void updateAccountStatus_successTrue_rejectAccount() {
		boolean success = AccountDAO.updateAccountStatus("caramel6", "1");
		assertTrue(success);
	}

	@Test
	void updateAccountStatus_successTrue_approveAccount() {
		boolean success = AccountDAO.updateAccountStatus("caramel6", "2");
		assertTrue(success);
	}
	
	@Test
	void updateAccountStatus_successFalse_bothNullInput() {
		boolean success = AccountDAO.updateAccountStatus(null, null);
		assertFalse(success);
	}
	
	@Test
	void updateAccountStatus_successFalse_nullUsername() {
		boolean success = AccountDAO.updateAccountStatus(null, "1");
		assertFalse(success);
	}
	
	@Test
	void updateAccountStatus_successFalse_invalidUsername() {
		boolean success = AccountDAO.updateAccountStatus("xX_The_Sasuke_Lover_Xx", "2");
		assertFalse(success);
	}
	
	@Test
	void updateAccountStatus_successFalse_nullStatus() {
		boolean success = AccountDAO.updateAccountStatus("Uchiha_Lover", null);
		assertFalse(success);
	}
	
	@Test
	void updateAccountStatus_successFalse_invalidStatus() {
		boolean success = AccountDAO.updateAccountStatus("caramel6", "s");
		assertFalse(success);
	}
	
	
}
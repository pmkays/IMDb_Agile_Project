package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.dao.utils.DatabaseUtils;
import app.model.ActorImage;
import app.model.CreditsRoll;
import app.model.Person;
import app.model.Show;

public class PersonDAO {

	public static Person getPersonById(String id) {
		Person person = null;
		try {
			String sql = "SELECT * FROM person, actor_image WHERE person.person_id = actor_image.person_id "
					+ "AND person.person_id ='" + id + "'";

			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			result.next();

			int personId = result.getInt("person_id");
			String fullname = result.getString("fullname");
			String role = result.getString("role");
			Date birthdate = result.getDate("birthdate");
			String bio = result.getString("bio");

			ActorImage image = new ActorImage(result.getInt("image_id"), result.getString("url"),
					result.getInt("person_id"));

			person = new Person(personId, fullname, role, birthdate, bio);
			person.setActorImage(image);

			DatabaseUtils.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (person != null) {
			return person;
		}
		return null;
	}

	public static List<Person> getAllPersonsByNameFilter(String name) {
		List<Person> actors = new ArrayList<>();

		try {
			String sql = "SELECT * FROM 'person' WHERE upper(fullname) like '%' + name.toUpperCase() + '%'";

			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				actors.add(new Person(result.getInt("person_id"), result.getString("fullname"),
						result.getString("role"), result.getDate("birthdate"), result.getString("bio")));
			}
			DatabaseUtils.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return actors;
	}

	public static List<Person> searchPerson(String name) {
		List<Person> actors = new ArrayList<>();

		try {
			String sql = "SELECT * FROM person, actor_image WHERE person.person_id = actor_image.person_id "
					+ "AND UPPER(person.fullname) LIKE '%" + name.toUpperCase() + "%'";

			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				Person personResult = new Person(result.getInt("person_id"), result.getString("fullname"),
						result.getString("role"), result.getDate("birthdate"), result.getString("bio"));

				ActorImage image = new ActorImage(result.getInt("image_id"), result.getString("url"),
						result.getInt("person_id"));

				personResult.setActorImage(image);

				actors.add(personResult);
			}
			DatabaseUtils.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return actors;
	}

	public static List<CreditsRoll> getCreditsRollByPerson(Person person) {
		List<CreditsRoll> creditRolls = new ArrayList<CreditsRoll>();
		CreditsRoll creditRoll = null;
		try {
			String sql = "SELECT * FROM credits_roll WHERE person_id ='" + person.getPersonId() + "'";

			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				String role = result.getString("role");
				int showId = result.getInt("show_id");
				int startYear = result.getInt("start_year");
				int endYear = result.getInt("end_year");
				String character = result.getString("character_name");

				Show show = ShowDAO.getShowByID(Integer.toString(showId));

				creditRoll = new CreditsRoll(person, role, startYear, character, endYear, show);

				creditRolls.add(creditRoll);
			}
			DatabaseUtils.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!creditRolls.isEmpty()) {
			return creditRolls;
		}
		return null;
	}

}

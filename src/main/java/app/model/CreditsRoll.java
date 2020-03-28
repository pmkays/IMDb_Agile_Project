package app.model;


public class CreditsRoll {
    private Person person;
    private int personID;
    private String role;
    private String character;
    private int startYear;
    private int endYear;

    public CreditsRoll(Person person, String role, int startYear, String character, int endYear) {
    	this.person = person;
        this.role = role;
        this.startYear = startYear;
        this.character = character;
        this.endYear = endYear;
    }

    public Person getPerson() {
        return person;
    }
    
    public int getPersonID() {
        return this.personID;
    }

    public String getRole() {
        return role;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public String getCharacter() {
        return character;
    }
}

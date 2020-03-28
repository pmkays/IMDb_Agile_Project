package app.model;


public class CreditsRoll {
    private Person person;
    private int personID;
    private String role;
    private String character;
    private int startYear;
    private int endYear;

    public CreditsRoll(int personID, String role, int startYear) {
        this.personID = personID;
        this.role = role;
        this.startYear = startYear;
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

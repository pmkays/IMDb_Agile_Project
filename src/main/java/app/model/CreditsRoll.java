package app.model;


public class CreditsRoll {
    private Person person;
    private int personID;
    private String role;
    private String character;
    private int startYear;
    private int endYear;
    private int showID;
    private Show show;

    public CreditsRoll(Person person, String role, int startYear, String character, int endYear, int showID) {
    	this.person = person;
        this.role = role;
        this.startYear = startYear;
        this.character = character;
        this.endYear = endYear;
        this.showID = showID;
    }
    
    public CreditsRoll(Person person, String role, int startYear, String character, int endYear, Show show) {
    	this.person = person;
        this.role = role;
        this.startYear = startYear;
        this.character = character;
        this.endYear = endYear;
        this.show = show;
    }
    
    public CreditsRoll(int personID, String role, int startYear, String character, int endYear, Show show) {
    	this.personID = personID;
        this.role = role;
        this.startYear = startYear;
        this.character = character;
        this.endYear = endYear;
        this.show = show;
    }
    
    public CreditsRoll(int personID, String role, String character, int startYear, int endYear, int showID) {
    	this.personID = personID;
        this.role = role;
        this.startYear = startYear;
        this.character = character;
        this.endYear = endYear;
        this.showID = showID;
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
    
    public int getShowID() {
    	return showID;
    }
    
    public Show getShow() {
    	return show;
    }
}

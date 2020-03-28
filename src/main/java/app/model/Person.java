package app.model;



import java.util.Date;



public class Person {
    private int personId;
    private String fullName;
    private String role;
    private Date birthdate;
    private String bio;

    public Person(int id, String fullName, String role, Date bday, String bio) {
        this.personId = id;
        this.fullName = fullName;
        this.role = role;
        this.birthdate = bday;
        this.bio = bio;
    }
    
    public int getPersonId() {
        return this.personId;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getRole() {
        return this.role;
    }
    
    public Date getBirthdate() {
        return this.birthdate;
    }
    
    public String getBio() {
        return this.bio;
    }

}

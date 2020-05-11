package app.model;



public class Account {
    private String firstName;
    private String lastName;
    private String username;

    /**
     * Only stores hashed passwords.
     */
    private String password;

    private String country;
    private String gender;
    private String email;
    private String role;
    
   //m3 additions to match with form 
    private String postCode; 
    private String year;
    private String organisationName; 
    private String organisationNumber;
    private int status;

    public Account(String un, String p) {
        username = un;
        password = p;
    }

    public Account(String un, String p, String fn, String ln, String c, String g, 
    		String email, String role) {
        // TODO fill in here
        /* You should use this constructor when you are showing the account page,
        hence, the user is already logged in. Therefore, the username Should be used
        to fetch this information from the database. You may have to tweek some stuff
        here and there.
        You should NEVER show the current password in the form. NEVER!
        And if you want to change the password, you need to ask for current password as well.
         */
    	this.username = un;
    	this.password = p;
    	this.firstName = fn;
    	this.lastName = ln;
    	this.country = c;
    	this.gender = g;
    	this.email = email;
    	this.role = role;
    }
    public Account(String un, String p, String fn, String ln, String c, String g, 
    		String email, String role, String postCode, String year, String organisationName, String organisationNumber, int status) {
    	this.username = un;
    	this.password = p;
    	this.firstName = fn;
    	this.lastName = ln;
    	this.country = c;
    	this.gender = g;
    	this.email = email;
    	this.role = role;
    	this.postCode = postCode; 
    	this.year = year;
    	this.organisationName = organisationName;
    	this.organisationNumber = organisationNumber;
    	this.status = status;
    }

    public void updateDetails() {
        // TODO
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCountry() {
		return country;
	}

	public String getGender() {
		return gender;
	}

	public String getEmail() {
		return email;
	}
	
	public String getRole() {
		return role;
	}
	
	public String getPostCode() {
		return postCode;
	}
	
	public String getOrganisationName() {
		return organisationName;
	}
	
	public String getOrganisationNumber() {
		return organisationNumber;
	}
	
	public String getYear() {
		return year;
	}
	
	public int getStatus() {
		return status;
	}
}

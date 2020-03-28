package app.model;

public class ActorImage {
	
	private int imageID;
	private String url;
	private int personID;
	
	public ActorImage(int id, String url, int personID) {
		this.imageID = id;
		this.url = url;
		this.personID = personID;
	}

	public int getImageID() {
		return imageID;
	}

	public String getUrl() {
		return url;
	}
	
	public int personID() {
		return personID;
	}

}

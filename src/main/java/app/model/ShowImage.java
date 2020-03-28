package app.model;

public class ShowImage {
	
	private int imageID;
	private String url;
	
	public ShowImage(int id, String url) {
		this.imageID = id;
		this.url = url;
	}

	public int getImageID() {
		return imageID;
	}

	public String getUrl() {
		return url;
	}

}

package app.model;

public class ShowImage {
	
	private int imageID;
	private String url;
	private int showID;
	
	public ShowImage(int id, String url) {
		this.imageID = id;
		this.url = url;
	}
	
	public ShowImage(String url, int showID) {
		this.url = url;
		this.showID = showID;
	}

	public int getImageID() {
		return imageID;
	}

	public String getUrl() {
		return url;
	}
	
	public int getShowID() {
		return showID;
	}

}

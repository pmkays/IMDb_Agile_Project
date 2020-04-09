package app.model;


import java.util.List;


public class Show {
    private int showid;
    private String showTitle;
    private String genre;
    private double length;
    private boolean isMovie;
    private boolean isSeries;
    private int year;
    private String synopsis;

    private List<UserReview> userReviewList;
    private ProductionCompany productionCompany;
    private List<ShowImage> images;

    private List<CreditsRoll> creditsRolls;

    
    public Show(int showid, String showTitle, double length,
    		boolean isMovie, boolean isSeries, ProductionCompany productionCompany,
    		String genre, int year, String synopsis) {
    	this.showid = showid;
    	this.showTitle = showTitle;
    	this.length = length;
    	this.isMovie = isMovie;
    	this.isSeries = isSeries;
    	this.productionCompany = productionCompany;
    	this.genre = genre;
    	this.year = year;
    	this.synopsis = synopsis;
    	
    }
    
    public Show(int showid) {
		this.showid = showid;
	}

	public int getShowID() {
    	return showid;
    }
    
    public String getShowTitle() {
    	return showTitle;
    }
    
    public double getLength() {
    	return length;
    }
    
    public boolean getIsMovie() {
    	return isMovie;
    }
    
    public boolean getIsSeries() {
    	return isSeries;
    }
    
    public ProductionCompany getProco() {
    	return productionCompany;
    }
    
    public String getGenre() {
    	return genre;
    }
    
    public int getYear() {
    	return year;
    }
    
    public String getSynopsis() {
    	return synopsis;
    }
    
    public void setImages(List<ShowImage> images){
    	this.images = images;
    }
    
    public List<ShowImage> getImages(){
    	return this.images;
    }



}

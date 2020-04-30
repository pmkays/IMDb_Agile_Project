package app.model;

import java.util.Date;

public class UserReview {
	private int reviewID;
	private int showID;
	private String userID;
	private int rating;
	private String title;
	private String review;
	private Date date;
	private String showName;

	public UserReview(int reviewID, int showID, String userID, int rating, String title, String review, Date date) {
		this.reviewID = reviewID;
		this.showID = showID;
		this.userID = userID;
		this.rating = rating;
		this.title = title;
		this.review = review;
		this.date = date;
	}

	public UserReview(int reviewID, int showID, String userID, int rating, String title, String review, Date date,
			String showName) {
		this.reviewID = reviewID;
		this.showID = showID;
		this.userID = userID;
		this.rating = rating;
		this.title = title;
		this.review = review;
		this.date = date;
		if (showName.length() >= 30)
			this.showName = String.format("%s...", showName.substring(0,30));
		else {
			this.showName = showName;
		}
	}

	public String getShowName() {
		return showName;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Date getDate() {
		return this.date;
	}

	public int getReviewID() {
		return this.reviewID;
	}

	public int getShowID() {
		return this.showID;
	}

	public String getUserID() {
		return this.userID;
	}

	public String getTitle() {
		return title;
	}

}

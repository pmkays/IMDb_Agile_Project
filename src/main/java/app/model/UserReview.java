package app.model;

import java.util.Date;

public class UserReview {
    private int reviewID;
    private int showID;
    private String userID;
    private int rating;
    private String review;
    private Date date;

    public UserReview(int reviewID, int showID, String userID, int rating,
    				String review, Date date) {
        this.reviewID = reviewID;
        this.showID = showID;
        this.userID = userID;
        this.rating = rating;
        this.review = review;
        this.date = date;
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
        return date;
    }
}

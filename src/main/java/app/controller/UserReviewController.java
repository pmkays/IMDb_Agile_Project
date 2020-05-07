package app.controller;

import java.util.List;
import java.util.Map;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.dao.ShowDAO;
import app.dao.UserReviewDAO;
import app.model.Show;
import app.model.UserReview;
import io.javalin.http.Handler;

public class UserReviewController {

    public static Handler serveReviewForm = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        String showID = ctx.queryParam("show");
        String userID = ctx.sessionAttribute("currentUser");
    	Show show = ShowDAO.getShowByID(showID);
        
        UserReview userReview = UserReviewDAO.getReviewByShowAndUser(showID, userID);
        
        if(userReview == null) {
            model.put("show", show);
            model.put("review", false);
        } else {
        	model.put("show", show);
            model.put("review", userReview);
        }
        
        ctx.render(Template.USER_REVIEW_FORM, model);
    };

    public static Handler serveReviewSubmit = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        int rating = Integer.parseInt(ctx.formParam("rating"));
        String title = ctx.formParam("title");
        String review = ctx.formParam("review");
        String showID = ctx.formParam("showID");
        String edit = ctx.formParam("edit");
        String userID = ctx.sessionAttribute("currentUser");
        
        if(edit.trim().equals("yes")) {
        	UserReviewDAO.updateReview(showID, userID, rating, title, review);
            model.put("currUser", userID);
            model.put("showID", showID);
            ctx.render(Template.USER_REVIEW_SUBMIT, model);
        }else {
        	UserReviewDAO.insertReview(showID, userID, rating, title, review);
            model.put("currUser", userID);
            model.put("showID", showID);
            ctx.render(Template.USER_REVIEW_SUBMIT, model);
        }
    };
    
    public static Handler serveReviewPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        // You'll have to update the model... maybe here
        String showID = ctx.queryParam("show");

        List<UserReview> reviews = UserReviewDAO.getReviewByShowID(showID);
        model.put("reviews", reviews);
        ctx.render(Template.USER_REVIEW, model);
    };




}

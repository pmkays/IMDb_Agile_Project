package app.controller;

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
        Show show = ShowDAO.getShowByID(showID);
        model.put("show", show);
        ctx.render(Template.USER_REVIEW_FORM, model);
    };

    public static Handler serveReviewSubmit = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        int rating = Integer.parseInt(ctx.formParam("rating"));
        String title = ctx.formParam("title");
        String review = ctx.formParam("review");
        String showID = ctx.formParam("showID");
        String userID = ctx.sessionAttribute("currentUser");
        
        UserReviewDAO.insertReview(showID, userID, rating, title, review);
        model.put("currUser", userID);
        ctx.render(Template.USER_REVIEW_SUBMIT, model);
    };
    
    public static Handler serveReviewPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        // You'll have to update the model... maybe here

        UserReview review = UserReviewDAO.getReviewByID("1");
        model.put("review", review);
        ctx.render(Template.USER_REVIEW, model);
    };




}

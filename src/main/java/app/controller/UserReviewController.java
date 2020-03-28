package app.controller;

import java.util.Map;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.dao.UserReviewDAO;
import app.model.UserReview;
import io.javalin.http.Handler;



public class UserReviewController {

    public static Handler serveReviewPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        // You'll have to update the model... maybe here

        UserReview review = UserReviewDAO.getReviewByID("1");
        model.put("review", review);
        ctx.render(Template.USER_REVIEW, model);
    };




}

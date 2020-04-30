package app.controller;

import java.util.List;
import java.util.Map;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.dao.AccountDAO;
import app.dao.UserReviewDAO;
import app.model.Account;
import app.model.UserReview;
import io.javalin.http.Handler;



public class AccountController {

    public static Handler serveAccountPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        // You'll have to update the model... maybe here
        
        String username = ctx.sessionAttribute("currentUser");
        Account account = AccountDAO.getUserByUsername(username);
        List<UserReview> reviews = UserReviewDAO.getReviewsByUser(username);
        model.put("account", account);
        model.put("reviews", reviews);
        ctx.render(Template.ACCOUNT, model);
    };




}

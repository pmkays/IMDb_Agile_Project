package app.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

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
    
    public static Handler serveNewAccountForm = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);			
		
        // You'll have to update the model... maybe here
        ctx.render(Template.NEW_ACCOUNT, model);
    };
    
    public static Handler handleNewAccountForm = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        boolean success = false;
        String userName = ctx.formParam("username");
        String firstName = ctx.formParam("first_name");
        String lastName = ctx.formParam("last_name");
        String gender = ctx.formParam("gender");
        String year = ctx.formParam ("year");
        String country = ctx.formParam("country");
        String postCode = ctx.formParam("post_code");
        String email = ctx.formParam("email");
        String pass1 = ctx.formParam("password1");
        String pass2 = ctx.formParam("password2");
        String role = ctx.formParam("user_type");
        String orgName = ctx.formParam("organisation_name") == null ? "N/A" : ctx.formParam("organisation_name");
        String orgNum = ctx.formParam("organisation_number") == null ? "N/A" : ctx.formParam("organisation_number");   
        
        if(pass1.equals(pass2)) {
            String hashedPassword = BCrypt.hashpw(pass1, AccountDAO.SALT);        	
        	success = AccountDAO.addNewAccount(new Account(userName, hashedPassword, firstName, lastName, 
        			country, gender, email, role, postCode, year, orgName, orgNum, 0));     	
        }
        
        String output = "";
        
        if(success) {
        	output += "Your new account request has been received. A member of our team will process it shortly.";
        }else {
        	output += "There was an error with your new account request. Please try again.";
        }
        
        model.put("status", output);
        ctx.render(Template.FORM_OUTCOME, model);
        
        
    };



}

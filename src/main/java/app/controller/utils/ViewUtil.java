package app.controller.utils;


import app.controller.paths.Template;
import app.dao.AccountDAO;
import app.model.Account;
import io.javalin.http.Context;
import io.javalin.http.ErrorHandler;
import java.util.HashMap;
import java.util.Map;





public class ViewUtil {


    public static Map<String, Object> baseModel(Context ctx) {
        Map<String, Object> model = new HashMap<>();
        model.put("currentUser", RequestUtil.getSessionCurrentUser(ctx));
        if(RequestUtil.getSessionCurrentUser(ctx) != null)
        {
            Account account = AccountDAO.getUserByUsername(RequestUtil.getSessionCurrentUser(ctx));
            model.put("currentUserRole", account.getRole());
            model.put("currentProcoID", account.getProcoId());
        }
        return model;
    }

    public static ErrorHandler notFound = ctx -> {
        ctx.render(Template.NOT_FOUND, baseModel(ctx));
    };

}

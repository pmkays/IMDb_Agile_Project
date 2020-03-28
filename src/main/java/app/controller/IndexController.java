package app.controller;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.dao.PersonDAO;
import app.model.Person;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.Map;




public class IndexController {


    public static Handler serveIndexPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        
        if(!getQueryUsername(ctx))
        {
        	ctx.render(Template.INDEX, model);
        }
        else
        {
//        	model.put("robertid", ctx.queryParam("robert"));
        	Person person = PersonDAO.getPersonById(ctx.queryParam("actor"));
        	model.put("actor", person);
        	ctx.render(Template.PERSON, model);
        }
        	
    };
    
    public static Handler handleRobert = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
//        PersonDAO.getPersonById(id);
    };

    
    public static boolean getQueryUsername(Context ctx) {
        return ctx.queryParam("actor")!=null;
    }


}

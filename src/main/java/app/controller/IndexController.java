package app.controller;

import app.controller.paths.Template;
import app.controller.paths.Web;
import app.controller.utils.ViewUtil;
import app.dao.PersonDAO;
import app.model.CreditsRoll;
import app.model.Person;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.List;
import java.util.Map;




public class IndexController {


    public static Handler serveIndexPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        
        if(!getQueryActor(ctx)){
        	ctx.render(Template.INDEX, model);
        }
        else{
        	Person person = PersonDAO.getPersonById(ctx.queryParam("actor"));
        	List<CreditsRoll> creditRolls = PersonDAO.getCreditsRollByPerson(person);
        	model.put("actor", person);
        	model.put("credits", creditRolls);
        	model.put("show_link", Web.SHOW);
        	ctx.render(Template.PERSON, model);
        }
        	
    };
    
    public static Handler handleRobert = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
//        PersonDAO.getPersonById(id);
    };

    
    public static boolean getQueryActor(Context ctx) {
        return ctx.queryParam("actor")!=null;
    }


}

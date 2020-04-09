package app.controller;

import java.util.Map;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import io.javalin.http.Handler;




public class IndexController {


    public static Handler serveIndexPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        String queryType = ctx.queryParam("queryType");
    	String searchQuery = ctx.queryParam("searchQuery");
        if(queryType == null){
        	ctx.render(Template.INDEX, model);
        }
        else if(queryType.equals("actorshow")){
        	ctx.redirect("/searchByActor?showActorSearch="+searchQuery);
        }
        else if(queryType.equals("show")) {
        	ctx.redirect("/searchresults?showTitleSearch="+searchQuery);
        }
        else if(queryType.equals("actor")) {
        	ctx.redirect("/searchForActor?searchQuery="+searchQuery);
        }
        	
    };

}

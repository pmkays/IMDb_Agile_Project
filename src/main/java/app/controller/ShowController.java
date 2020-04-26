package app.controller;
import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.dao.ProCoDAO;
import app.dao.ShowDAO;
import app.model.CreditsRoll;
import app.model.ProductionCompany;
import app.model.Show;
import io.javalin.http.Handler;

import java.util.List;
import java.util.Map;


public class ShowController
{
    public static Handler serveShowPage = ctx ->
    {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        // You'll have to update the model... maybe here

        Show show = ShowDAO.getShowByID(ctx.queryParam("show"));
        List<CreditsRoll> creditsRoll = ShowDAO.getCreditsRollByShowID(ctx.queryParam("show"));
        String duration = Double.toString(show.getLength());

        model.put("show", show);
        model.put("hour",duration.substring(0,1));
        model.put("minutes", duration.substring(2));
        model.put("credits", creditsRoll);
        ctx.render(Template.SHOW, model);
    };

    public static Handler serveResultsPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);

        String searchText = ctx.queryParam("showTitleSearch");
        List<Show> shows = ShowDAO.getAllShowsByTitleFilter(searchText);
        model.put("shows", shows);
        model.put("query", searchText);
        ctx.render(Template.SEARCH_RESULTS, model);
    };

    public static Handler serveSearchByActorPage = ctx ->
    {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        String searchText = ctx.queryParam("showActorSearch");
        List<Show> shows = ShowDAO.getAllShowsByPersonFilter(searchText);
        model.put("shows", shows);
        model.put("query", searchText);
        ctx.render(Template.SEARCH_BY_ACTOR, model);
    };
    
    public static Handler serveAddNewShowForm = ctx ->{
    	Map<String, Object> model = ViewUtil.baseModel(ctx);
    	ctx.render(Template.NEW_SHOW_FORM, model);
    };
    	
    
    public static Handler handleAddNewShowForm = ctx ->{
    	Map<String, Object> model = ViewUtil.baseModel(ctx);
    	String title = ctx.formParam("show_title");
    	String genre = ctx.formParam("genre");
    	Double length = Double.parseDouble(ctx.formParam("length"));
    	int type = Integer.parseInt(ctx.formParam("type"));
    	ProductionCompany proco = ProCoDAO.getProductionCompanyByName(ctx.formParam("production_company"));
    	int year = Integer.parseInt(ctx.formParam("year"));
    	String synopsis = ctx.formParam("synopsis");
    	int status = Integer.parseInt(ctx.formParam("status"));	
    	
    	Show showToAdd = new Show(title, length, type, proco, genre, year, synopsis, status);
    	
    	if(ShowDAO.AddNewShow(showToAdd)){
    		model.put("status", "Your new show entry has been submitted and will "
    				+ "be reviewed by a member of our team. Thank you.");
    	}else {
    		model.put("status", "Your new show entry has failed. Please try again");
    	}
    	ctx.render(Template.NEW_SHOW_FORM_OUTCOME, model);
    };
    
}
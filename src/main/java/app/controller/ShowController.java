package app.controller;

import java.util.List;
import java.util.Map;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.dao.ShowDAO;
import app.model.CreditsRoll;
import app.model.Show;
import io.javalin.http.Context;
import io.javalin.http.Handler;



public class ShowController {

    public static Handler serveShowPage = ctx -> {
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

}

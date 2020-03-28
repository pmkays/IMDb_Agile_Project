package app.controller;

import java.util.List;
import java.util.Map;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.dao.ShowDAO;
import app.model.CreditsRoll;
import app.model.Show;
import io.javalin.http.Handler;



public class ShowController {

    public static Handler serveShowPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        // You'll have to update the model... maybe here

        Show show = ShowDAO.getShowByID("1");
        List<CreditsRoll> creditsRoll = ShowDAO.getCreditsRollByShowID("1");
        model.put("show", show);
        model.put("credits", creditsRoll);
        ctx.render(Template.SHOW, model);
    };




}

package app.controller;

import java.util.List;
import java.util.Map;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.dao.ShowDAO;
import app.model.Show;
import io.javalin.http.Handler;



public class ShowController {

    public static Handler serveShowPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        // You'll have to update the model... maybe here

        Show show = ShowDAO.getShowByID("1");
        model.put("show", show);
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





}

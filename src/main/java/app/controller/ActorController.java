package app.controller;

import java.util.List;
import java.util.Map;

import app.controller.paths.Template;
import app.controller.paths.Web;
import app.controller.utils.ViewUtil;
import app.dao.PersonDAO;
import app.model.CreditsRoll;
import app.model.Person;
import io.javalin.http.Handler;

public class ActorController {
	public static Handler serveActorPage = ctx -> {
		Map<String, Object> model = ViewUtil.baseModel(ctx);

		Person person = PersonDAO.getPersonById(ctx.queryParam("actor"));
		List<CreditsRoll> creditRolls = PersonDAO.getCreditsRollByPerson(person);
		model.put("actor", person);
		model.put("credits", creditRolls);
		model.put("show_link", Web.SHOW);
		ctx.render(Template.PERSON, model);
	};
	
	public static Handler serveSearchForActorPage = ctx -> {
		Map<String, Object> model = ViewUtil.baseModel(ctx);
		String queryParam = ctx.queryParam("searchQuery");
		List<Person> persons = PersonDAO.searchPerson(queryParam);
		model.put("persons", persons);
		model.put("query", queryParam);
		ctx.render(Template.SEARCH_FOR_ACTOR, model);
	};
}

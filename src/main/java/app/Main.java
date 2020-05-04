package app;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;

import app.controller.AccountController;
import app.controller.ActorController;
import app.controller.IndexController;
import app.controller.LoginController;
import app.controller.ShowController;
import app.controller.UserReviewController;
import app.controller.paths.Web;
import app.controller.utils.ViewUtil;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;

public class Main {

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public");
            config.registerPlugin(new RouteOverviewPlugin("/routes"));
        }).start(getHerokuAssignedPort());

        app.routes(() -> {
            // You will have to update this, to limit who can see the reviews
            // before(LoginController.ensureLoginBeforeViewing);

            get(Web.INDEX, IndexController.serveIndexPage);

            get(Web.LOGIN, LoginController.serveLoginPage);
            post(Web.LOGIN, LoginController.handleLoginPost);
            post(Web.LOGOUT, LoginController.handleLogoutPost);

            get(Web.ACCOUNT, AccountController.serveAccountPage);
            get(Web.SHOW, ShowController.serveShowPage);
            get(Web.USER_REVIEW_FORM, UserReviewController.serveReviewForm);
            post(Web.USER_REVIEW_SUBMIT, UserReviewController.serveReviewSubmit);
            get(Web.USER_REVIEW, UserReviewController.serveReviewPage);
            get(Web.SEARCH_BY_ACTOR, ShowController.serveSearchByActorPage);
            get(Web.SEARCH_FOR_ACTOR, ActorController.serveSearchForActorPage);
            get(Web.SEARCH_RESULTS, ShowController.serveResultsPage);
            get(Web.ACTOR, ActorController.serveActorPage);
           
            get(Web.ADD_SHOW, ShowController.serveAddNewShowForm);
            post(Web.ADD_SHOW, ShowController.handleAddNewShowForm);
            
            get(Web.EDIT_SHOW, ShowController.serveEditShowForm);
            post(Web.EDIT_SHOW, ShowController.handleEditShowForm);

            post(Web.DELETE_SHOW, ShowController.handleDeleteShowButton);

            get(Web.SHOW_BY_STATUS, ShowController.serveShowsByStatusPage);

            // Add new actions here
            // Seeing pages (get) and sending information in forms (post)
        });

        app.error(404, ViewUtil.notFound);
    }






    public static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 7000;
    }



}

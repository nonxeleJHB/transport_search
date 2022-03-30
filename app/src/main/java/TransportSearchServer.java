import database.Db;
import home.HomeController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import static io.javalin.apibuilder.ApiBuilder.post;

class TransportSearchServer {
    private static final int DEFAULT_PORT = 7001;
    private static final String STATIC_DIR = "/html";
    private final Javalin app;

    public TransportSearchServer() {

        app = createAndConfigureServer();
        setupRoutes(app);
    }

    private Javalin createAndConfigureServer() {
        return Javalin.create(config -> {
            config.addStaticFiles(STATIC_DIR, Location.CLASSPATH);
            config.sessionHandler(Sessions::nopersistSessionHandler);
        });


    }
    public static void main(String[] args) {
        Db.createNewDatabase();
        Db.createTaxiRankTable();
        Db.createRouteTable();
        Db.createLinkTable();
        TransportSearchServer server = new TransportSearchServer();
        server.start(DEFAULT_PORT);
    }

    public void start(int port) {
        app.start(port);
    }

    private void setupRoutes(Javalin server) {
        server.routes(this::homePageRoute);
    }

    private void homePageRoute() {
        post(HomeController.HOME_PATH, HomeController::handleHome);
    }
}
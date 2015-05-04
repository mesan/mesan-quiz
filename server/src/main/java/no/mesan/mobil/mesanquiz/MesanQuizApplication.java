package no.mesan.mobil.mesanquiz;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import no.mesan.mobil.mesanquiz.config.MesanQuizConfiguration;
import no.mesan.mobil.mesanquiz.dao.GameDao;
import no.mesan.mobil.mesanquiz.resource.GameResource;
import no.mesan.mobil.mesanquiz.service.GameService;
import org.skife.jdbi.v2.DBI;

public class MesanQuizApplication extends Application<MesanQuizConfiguration> {

    public static String IMAGE_BASE_URL = null;
    public static final String IMAGE_URL_SUFFIX = ".png";


    public static void main(String[] args) throws Exception {
        new MesanQuizApplication().run(args);
    }

    @Override
    public String getName() {
        return "mesanquiz";
    }

    @Override
    public void initialize(Bootstrap<MesanQuizConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(MesanQuizConfiguration configuration, Environment environment) {
        IMAGE_BASE_URL = configuration.getImageServerBaseUrl();

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDatabaseConfiguration(), "postgresql");
        final GameDao gameDao = jdbi.onDemand(GameDao.class);

        final GameService gameService = new GameService(gameDao);


        final GameResource gameResource = new GameResource(gameService);
        environment.jersey().register(gameService);
        environment.jersey().register(gameResource);

        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
    }
}

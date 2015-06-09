package no.mesan.mobil.mesanquiz;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import no.mesan.mobil.mesanquiz.config.MesanQuizConfiguration;
import no.mesan.mobil.mesanquiz.dao.AlternativeDao;
import no.mesan.mobil.mesanquiz.dao.GameDao;
import no.mesan.mobil.mesanquiz.dao.PersonDao;
import no.mesan.mobil.mesanquiz.dao.QuestionDao;
import no.mesan.mobil.mesanquiz.dao.ScoreDao;
import no.mesan.mobil.mesanquiz.resource.GameResource;
import no.mesan.mobil.mesanquiz.resource.PersonResource;
import no.mesan.mobil.mesanquiz.service.AlternativeService;
import no.mesan.mobil.mesanquiz.resource.ScoreResource;
import no.mesan.mobil.mesanquiz.service.GameService;
import no.mesan.mobil.mesanquiz.service.PersonService;
import no.mesan.mobil.mesanquiz.service.QuestionService;
import no.mesan.mobil.mesanquiz.service.ScoreService;
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
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
        final GameDao gameDao = jdbi.onDemand(GameDao.class);
        final PersonDao personDao = jdbi.onDemand(PersonDao.class);
        final QuestionDao questionDao = jdbi.onDemand(QuestionDao.class);
        final AlternativeDao alternativeDao = jdbi.onDemand(AlternativeDao.class);

        final AlternativeService alternativeService = new AlternativeService(alternativeDao);
        final QuestionService questionService = new QuestionService(questionDao, alternativeService);
        final GameService gameService = new GameService(gameDao, questionService);
        final PersonService personService = new PersonService(personDao);

        final ScoreDao scoreDao = jdbi.onDemand(ScoreDao.class);
        final ScoreService scoreService = new ScoreService(scoreDao);
        final ScoreResource scoreResource = new ScoreResource(scoreService);
        environment.jersey().register(scoreService);
        environment.jersey().register(scoreResource);

        final GameResource gameResource = new GameResource(gameService, scoreService);
        environment.jersey().register(gameService);
        environment.jersey().register(gameResource);

        final PersonResource personResource = new PersonResource(personService);
        environment.jersey().register(personService);
        environment.jersey().register(personResource);

        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
    }


}

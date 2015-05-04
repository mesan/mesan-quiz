package no.mesan.mobil.mesanquiz;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MesanQuizApplication extends Application<MesanQuizConfiguration> {
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
    public void run(MesanQuizConfiguration configuration,
                    Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}

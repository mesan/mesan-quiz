package no.mesan.mobil.mesanquiz.config;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;

import javax.security.auth.login.Configuration;
import java.net.URI;
import java.net.URISyntaxException;

public class MesanQuizDbConfiguration {

    private final static String SSL = "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
    private final static String JDBC_PSQL = "jdbc:postgresql://";

    private static DatabaseConfiguration databaseConfiguration;

    protected static DatabaseConfiguration create(String databaseUrl) {
        try {
            URI dbUri = new URI(databaseUrl);
            final String user = dbUri.getUserInfo().split(":")[0];
            final String password = dbUri.getUserInfo().split(":")[1];
            final String url = JDBC_PSQL + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + SSL;
            databaseConfiguration = new DatabaseConfiguration() {
                DataSourceFactory dataSourceFactory;

                @Override
                public DataSourceFactory getDataSourceFactory(io.dropwizard.Configuration configuration) {
                    if (dataSourceFactory != null) {
                        return dataSourceFactory;
                    }
                    DataSourceFactory dsf = new DataSourceFactory();
                    dsf.setUser(user);
                    dsf.setPassword(password);
                    dsf.setUrl(url);
                    dsf.setDriverClass("org.postgresql.Driver");
                    dataSourceFactory = dsf;
                    return dsf;
                }
            };
        } catch (URISyntaxException e) {
        }

        return databaseConfiguration;
    }

    public DataSourceFactory getDataSourceFactory() {
        if (databaseConfiguration == null) {
            throw new IllegalStateException("You must first call DatabaseConfiguration.create(dbUrl)");
        }
        return databaseConfiguration.getDataSourceFactory(null);
    }
}

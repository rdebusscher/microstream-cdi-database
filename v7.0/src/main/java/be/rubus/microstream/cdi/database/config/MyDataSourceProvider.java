package be.rubus.microstream.cdi.database.config;

import one.microstream.afs.sql.types.SqlDataSourceProvider;
import one.microstream.configuration.types.Configuration;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

/**
 * Class providing the Datasource to MicroStream for storing the data in database target.
 */
public class MyDataSourceProvider implements SqlDataSourceProvider {


    @Override
    public DataSource provideDataSource(Configuration configuration) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(configuration.get("url"));
        dataSource.setUser(configuration.get("user"));
        dataSource.setPassword(configuration.get("password"));

        return dataSource;
    }
}


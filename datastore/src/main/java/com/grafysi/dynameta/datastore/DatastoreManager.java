package com.grafysi.dynameta.datastore;

import com.grafysi.dynameta.datastore.resource.ResourceStore;
import com.grafysi.dynameta.datastore.resource.ResourceStoreImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class DatastoreManager {

    private static final Logger logger = LoggerFactory.getLogger(DatastoreManager.class);

    private static final DataSource dataSource;

    @lombok.Getter
    private static final ResourceStore resourceStore;

    static {
        dataSource = createDataSource();
        resourceStore = createResourceStore();
    }

    private static ResourceStore createResourceStore() {
        return new ResourceStoreImpl(dataSource);
    }

    private static DataSource createDataSource() {
        try {
            var properties = new Properties();
            properties.load(DatastoreManager.class
                    .getClassLoader().getResourceAsStream("datastore.properties"));
            var dataSource = new HikariDataSource(new HikariConfig(properties));

            Runtime.getRuntime().addShutdownHook(new Thread(dataSource::close));
            return dataSource;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load datastore properties", e);
        }
    }

    private DatastoreManager() {
    }
}

















package com.grafysi.dynameta.context;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@ComponentScan(basePackages = {"com.grafysi.dynameta.context"})
@PropertySource("classpath:datastore.properties")
public class SpringConfiguration {

    @Value("classpath:datastore.properties")
    private Resource dataStoreResource;

    @Bean
    public DataSource dataSource() throws IOException {
        var configuration = com.grafysi.dynameta.utils.config.Configuration.load(dataStoreResource.getInputStream());
        var props = configuration.subset("db.postgres", true).asProperties();
        var dataSource = new HikariDataSource(new HikariConfig(props));
        Runtime.getRuntime().addShutdownHook(new Thread(dataSource::close));
        return dataSource;
    }
}

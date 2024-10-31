package com.grafysi.dynameta.server.config;

import com.grafysi.dynameta.datastore.DatastoreManager;
import com.grafysi.dynameta.datastore.resource.ResourceStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.grafysi.dynameta.server"})
@PropertySource("classpath:server.properties")
public class SpringConfiguration {

    @Bean
    public ResourceStore resourceStore() {
        return DatastoreManager.getResourceStore();
    }
}

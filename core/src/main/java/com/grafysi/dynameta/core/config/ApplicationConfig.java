package com.grafysi.dynameta.core.config;

import com.grafysi.dynameta.core.loader.PluginJarAndModuleRegistry;
import com.grafysi.dynameta.core.loader.PluginJarAndModuleRegistryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.grafysi.dynameta.core"})
public class ApplicationConfig {

    private static final String BASE_PLUGIN_MODULE = "base-plugin";

    @Bean
    PluginJarAndModuleRegistry pluginJarAndModuleRegistry() {
        var registry = new PluginJarAndModuleRegistryImpl();
        registry.registerModule(BASE_PLUGIN_MODULE);
        return registry;
    }

}

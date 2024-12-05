package com.grafysi.dynameta.core.dummyplugin;

import com.grafysi.dynameta.core.loader.PluginJarAndModuleRegistry;
import com.grafysi.dynameta.core.loader.PluginJarAndModuleRegistryImpl;
import com.grafysi.dynameta.dummyplugin.DummyStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.grafysi.dynameta.core")
public class DummyPluginTestConfig {

    private static final String DUMMY_PLUGIN_MODULE = "dummy-test-plugin";

    @Bean
    PluginJarAndModuleRegistry pluginJarAndModuleRegistry() {
        var registry = new PluginJarAndModuleRegistryImpl();
        registry.registerModule(DUMMY_PLUGIN_MODULE);
        return registry;
    }

    @Bean
    DummyStore dummyStore() {
        return new DummyStore();
    }
}






























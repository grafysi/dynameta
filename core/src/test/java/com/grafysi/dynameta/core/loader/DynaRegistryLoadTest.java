package com.grafysi.dynameta.core.loader;

import com.grafysi.dynameta.core.config.ApplicationConfig;
import com.grafysi.dynameta.core.plugin.DynaRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(classes = ApplicationConfig.class)
public class DynaRegistryLoadTest {

    @Autowired
    private DynaRegistry dynaRegistry;

    @Test
    void loadDynaRegistry() {
        assertNotNull(dynaRegistry);
    }

    @Test
    void countServicesAndFunctions() {
        assertEquals(1, dynaRegistry.getGrpcServices().size());
        assertEquals(1, dynaRegistry.getPlugins().size());
    }
}

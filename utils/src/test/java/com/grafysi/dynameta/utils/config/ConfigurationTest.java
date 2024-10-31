package com.grafysi.dynameta.utils.config;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConfigurationTest {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationTest.class);

    @Test
    void loadConfiguration() throws IOException {
        var config = Configuration.load("test-sample.properties", ConfigurationTest.class);
        assertNotNull(config);
        assertEquals(4, config.keys().size());
        logger.info(config.toString());
    }

    @Test
    void firstSubset() throws IOException {
        var config = Configuration.load("test-sample.properties", ConfigurationTest.class);
        var subset = config.subset("dynameta.test", true);
        assertEquals(2, subset.keys().size());
        logger.info(subset.toString());
    }

}




























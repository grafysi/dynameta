package com.grafysi.dynameta.api.config;

import java.util.Properties;

public interface Configurable {

    void configure(Properties properties);

    String propertyGroup();

}

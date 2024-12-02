package com.grafysi.dynameta.api.spi;

import com.grafysi.dynameta.api.server.GrpcService;
import com.grafysi.dynameta.api.server.FunctionHandler;

import java.util.Collections;
import java.util.List;
import java.util.Map;


public interface DynaPlugin {

    default Map<String, FunctionHandler<?, ?>> registeredMethodHandlers() {
        return Collections.emptyMap();
    }
}
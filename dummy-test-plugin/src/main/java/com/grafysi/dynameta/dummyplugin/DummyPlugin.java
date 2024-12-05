package com.grafysi.dynameta.dummyplugin;

import com.grafysi.dynameta.plugin.annotation.DynaPlugin;

@DynaPlugin(
        name = "dynameta-dummy-plugin",
        isTypeManager = false,
        grpcServicePackages = {"com.grafysi.dynameta.dummyplugin.services"},
        dynaFunctionPackages = {"com.grafysi.dynameta.dummyplugin.functions"}
)
public interface DummyPlugin {

    String DUMMY_CREATE = "DUMMY_CREATE";

    String DUMMY_GET = "DUMMY_GET";

}

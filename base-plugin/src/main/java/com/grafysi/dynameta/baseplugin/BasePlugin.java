package com.grafysi.dynameta.baseplugin;

import com.grafysi.dynameta.plugin.annotation.DynaPlugin;

@DynaPlugin(
        name = "base-plugin",
        parentPlugin = "",
        isTypeManager = false,
        grpcServicePackages = {"com.grafysi.dynameta.baseplugin.functions"},
        dynaFunctionPackages = {"com.grafysi.dynameta.baseplugin.services"}
)
public interface BasePlugin {

    String CREATE_RESOURCE_DEFINITION = "CREATE_RESOURCE_DEFINITION";

    String GET_RESOURCE_DEFINITION = "GET_RESOURCE_DEFINITION";

    String LIST_RESOURCE_DEFINITION = "LIST_RESOURCE_DEFINITION";
}

package com.grafysi.dynameta.baseplugin;

import com.grafysi.dynameta.api.annotation.PluginConfiguration;
import com.grafysi.dynameta.api.spi.DynaPlugin;

@PluginConfiguration(
        name = "base-plugin",
        extendedPlugin = "",
        isTypeManager = false,
        grpcServicePackages = {"com.grafysi.dynameta.baseplugin"},
        functionHandlerPackages = {"com.grafysi.dynameta.baseplugin"}
)
public interface BasePlugin extends DynaPlugin {

}

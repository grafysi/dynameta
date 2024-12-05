package com.grafysi.dynameta.core.plugin;

import com.grafysi.dynameta.plugin.PluginDynamics;

public interface PluginResolver {

    DynaRegistry.Plugin resolve(PluginDynamics.PluginSelector pluginSelector, Object request);

}

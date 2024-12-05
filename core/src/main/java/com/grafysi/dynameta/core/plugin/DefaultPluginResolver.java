package com.grafysi.dynameta.core.plugin;

import com.google.protobuf.Message;
import com.grafysi.dynameta.core.exception.PluginResolveException;
import com.grafysi.dynameta.plugin.PluginDynamics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class DefaultPluginResolver implements PluginResolver {

    private static final Logger logger = LoggerFactory.getLogger(DefaultPluginResolver.class);

    private static final String DEFAULT_FALLBACK_PLUGIN = "base-plugin";

    @Autowired
    @Lazy
    private DynaRegistry dynaRegistry;

    @Override
    public DynaRegistry.Plugin resolve(PluginDynamics.PluginSelector selector, Object request) {

        if (!selector.getWithPluginName().isBlank()) {
            return findPluginByName(selector.getWithPluginName());
        }

        if (!selector.getWithResourceTypeField().isBlank()) {
            var message = (Message) request;
            var typeField = message.getDescriptorForType()
                    .findFieldByName(selector.getWithResourceTypeField());
            var resourceType = (String) message.getField(typeField);
            return findPluginByResType(resourceType);
        }

        if (selector.hasWithResdefIdField()) {
            throw new RuntimeException("Not implemented");
        }

        if (selector.hasWithResdefNameField()) {
            throw new RuntimeException("Not implemented");
        }

        if (selector.hasWithResIdField()) {
            throw new RuntimeException("Not implemented");
        }

        if (selector.hasWithResNameField()) {
            throw new RuntimeException("Not implemented");
        }

        return findPluginByName(DEFAULT_FALLBACK_PLUGIN);
    }

    private DynaRegistry.Plugin findPluginByName(String pluginName) {
        return dynaRegistry.getPlugins()
                .stream()
                .filter(p -> p.getName().equals(pluginName))
                .findFirst()
                .orElseThrow(() -> new PluginResolveException(
                        "Plugin [" + pluginName + "] not found"));
    }

    private DynaRegistry.Plugin findPluginByResType(String resType) {
        return dynaRegistry.getPlugins()
                .stream()
                .filter(p -> p.isTypeManager() && p.getManagedResourceType().equals(resType))
                .findFirst()
                .orElseThrow(() -> new PluginResolveException(
                        "No plugin found for resource type [" + resType + "]"));
    }


}
























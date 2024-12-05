package com.grafysi.dynameta.core.plugin;

import com.grafysi.dynameta.core.exception.RegisteringException;
import com.grafysi.dynameta.plugin.PluginDynamics;
import com.grafysi.dynameta.plugin.function.DynaFunction;
import io.grpc.BindableService;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.*;

@Component
public class DynaRegistryImpl implements DynaRegistry {

    private static final String DEFAULT_PLUGIN_SELECTION = "base-plugin";

    private final Set<Plugin> plugins = new HashSet<>();

    private final PluginResolver pluginResolver;

    public DynaRegistryImpl(PluginResolver pluginResolver) {
        this.pluginResolver = pluginResolver;
    }

    @Override
    public void register(Plugin plugin) {
        plugins.add(checkPlugin(plugin));
    }

    private Plugin checkPlugin(Plugin plugin) {
        if (plugins.stream()
                .anyMatch(p ->
                        p.getName().equals(plugin.getName())
                                || (plugin.isTypeManager()
                                && p.isTypeManager()
                                && p.getManagedResourceType().equals(plugin.getManagedResourceType())))) {
            throw new RegisteringException(
                    "Plugin of the same name [" + plugin.getName() +"] has been registered");
        }
        return plugin;
    }

    @Override
    public Set<Plugin> getPlugins() {
        return Collections.unmodifiableSet(plugins);
    }

    private Plugin getPlugin(String searchName) {
        if (searchName == null) return null;
        return plugins.stream()
                .filter(p -> p.getName().equals(searchName))
                .findFirst()
                .orElse(null);
    }

    private Plugin resolve(PluginDynamics.PluginSelector selector, Object request) {
        return pluginResolver.resolve(selector, request);
    }

    @Override
    @Nullable
    public DynaFunction<?, ?> getDynaFunction(
            String name, Object request, PluginDynamics.PluginSelector pluginSelector) {
        return getDynaFunction(name, resolve(pluginSelector, request));
    }

    private DynaFunction<?, ?> getDynaFunction(String name, Plugin plugin) {
        var function = plugin.getDynaFunction(name);
        if (function != null) {
            return function;
        }
        var parent = getPlugin(plugin.getParentName());
        if (parent == null) {
            return null;
        }
        return getDynaFunction(name, parent);
    }

    @Override
    public List<BindableService> getGrpcServices() {
        return plugins.stream()
                .flatMap(p -> p.getGrpcServices().stream())
                .toList();
    }

    @Override
    public <T extends BindableService> List<? extends T> getGrpcServicesOfType(Class<T> serviceClass) {
        return (List<? extends T>) getGrpcServices()
                .stream()
                .filter(s -> serviceClass.isAssignableFrom(s.getClass()))
                .toList();
    }


}













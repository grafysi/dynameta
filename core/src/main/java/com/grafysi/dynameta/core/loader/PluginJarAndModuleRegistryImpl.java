package com.grafysi.dynameta.core.loader;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PluginJarAndModuleRegistryImpl implements PluginJarAndModuleRegistry {

    private final List<String> jars = new LinkedList<>();

    private final List<String> modules = new LinkedList<>();

    @Override
    public List<String> getJars() {
        return Collections.unmodifiableList(jars);
    }

    @Override
    public List<String> getModules() {
        return Collections.unmodifiableList(modules);
    }

    @Override
    public void registerJarsIn(Path... paths) {
        throw new RuntimeException("Un-implemented");
    }

    @Override
    public void registerModule(String... modules) {
        this.modules.addAll(Arrays.asList(modules));
    }

}




















package com.grafysi.dynameta.core.loader;

import java.nio.file.Path;
import java.util.List;

public interface PluginJarAndModuleRegistry {

    List<String> getJars();

    List<String> getModules();

    void registerJarsIn(Path... paths);

    void registerModule(String... modules);

}

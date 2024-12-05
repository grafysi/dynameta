package com.grafysi.dynameta.core.loader;

import com.grafysi.dynameta.plugin.annotation.GrpcMethod;
import com.grafysi.dynameta.plugin.annotation.DynaPlugin;
import io.github.classgraph.ClassGraph;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class PluginLoaderTest {

    @Test
    void testBasePlugin() {
        try (var scanResult = new ClassGraph()
                .enableClassInfo()
                .enableAnnotationInfo()
                .enableFieldInfo()
                .acceptModules("base-plugin")
                .scan()) {
            var classInfoList = scanResult.getClassesWithAnnotation(DynaPlugin.class);
            var first = classInfoList.getFirst();
            var anno = first.getFieldInfo().getFirst().getAnnotationInfo(GrpcMethod.class);
            assertEquals(1, classInfoList.size());
        }
    }
}

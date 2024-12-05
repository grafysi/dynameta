package com.grafysi.dynameta.core.loader;

import com.grafysi.dynameta.core.annotation.DependencyFieldResolver;
import com.grafysi.dynameta.core.plugin.DynaRegistry;
import com.grafysi.dynameta.core.server.GrpcServiceGenerator;
import com.grafysi.dynameta.plugin.annotation.DynaPlugin;
import com.grafysi.dynameta.plugin.annotation.FunctionConfiguration;
import com.grafysi.dynameta.plugin.annotation.GrpcService;
import com.grafysi.dynameta.plugin.annotation.Sharable;
import com.grafysi.dynameta.plugin.function.DynaFunction;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import io.grpc.BindableService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Component
public final class DynaAutoLoader implements InitializingBean {

    private final DependencyFieldResolver fieldResolver;

    private final GrpcServiceGenerator grpcServiceGenerator;

    private final PluginJarAndModuleRegistry jarAndModuleRegistry;

    private final DynaRegistry dynaRegistry;

    @Autowired
    public DynaAutoLoader(DynaRegistry dynaRegistry,
                          GrpcServiceGenerator grpcServiceGenerator,
                          PluginJarAndModuleRegistry jarAndModuleRegistry,
                          DependencyFieldResolver fieldResolver) {
        this.dynaRegistry = dynaRegistry;
        this.grpcServiceGenerator = grpcServiceGenerator;
        this.jarAndModuleRegistry = jarAndModuleRegistry;
        this.fieldResolver = fieldResolver;
    }

    @Override
    public void afterPropertiesSet() {
        loadDynaRegistry(dynaRegistry);
    }

    private void loadDynaRegistry(DynaRegistry dynaRegistry) {
        var classGraph = new ClassGraph()
                .enableClassInfo()
                .enableFieldInfo()
                .enableAnnotationInfo();
        jarAndModuleRegistry.getJars()
                .forEach(classGraph::acceptJars);
        jarAndModuleRegistry.getModules()
                .forEach(classGraph::acceptModules);

        try (var scanResult = classGraph.scan()) {
            for (var pluginClass : scanResult.getClassesWithAnnotation(DynaPlugin.class)) {
                var anno = pluginClass.loadClass().getAnnotation(DynaPlugin.class);
                var plugin = DynaRegistry.Plugin.builder()
                        .name(anno.name())
                        .parentName(anno.parentPlugin() == null ? null : anno.parentPlugin())
                        .isTypeManager(anno.isTypeManager())
                        .managedResourceType(anno.managedResourceType())
                        .addFunctionProviders(loadFunctionProviders(scanResult, anno.dynaFunctionPackages()))
                        .addGrpcServices(loadGrpcServices(scanResult, anno.grpcServicePackages()))
                        .build();
                dynaRegistry.register(plugin);
            }
        }
    }

    private HashMap<String, DynaObjectProvider<DynaFunction<?, ?>>> loadFunctionProviders(
            ScanResult scanResult, String... includedPackages) {
        var functionProviders =
                new HashMap<String, DynaObjectProvider<DynaFunction<?, ?>>>();
        for (var functionClass : scanResult.getClassesWithAnnotation(FunctionConfiguration.class)) {
            if (isSubpackage(functionClass.getPackageName(), includedPackages)) {
                var functionName = functionClass.loadClass()
                        .getAnnotation(FunctionConfiguration.class)
                        .name();
                functionProviders.put(functionName, createFunctionProvider(functionClass));
            }
        }
        return functionProviders;
    }

    private DynaObjectProvider<DynaFunction<?, ?>> createFunctionProvider(
            ClassInfo classInfo) {
        @SuppressWarnings("unchecked")
        var functionClass = (Class<DynaFunction<?, ?>>) (Class<?>) classInfo.loadClass(DynaFunction.class);
        return new DynaObjectProvider<>(
                functionClass,
                classInfo.hasAnnotation(Sharable.class),
                fieldResolver);
    }


    private List<BindableService> loadGrpcServices(ScanResult scanResult, String... includedPackages) {
        var grpcServices = new LinkedList<BindableService>();
        for (var serviceClass : scanResult.getClassesWithAnnotation(GrpcService.class)) {
            if (isSubpackage(serviceClass.getPackageName(), includedPackages)) {
                grpcServices.add(grpcServiceGenerator
                        .from(serviceClass.loadClass(BindableService.class)));
            }
        }
        return grpcServices;
    }

    private boolean isSubpackage(String target, String... parentPackages) {
        for (var parentPackage : parentPackages) {
            if (target.startsWith(parentPackage)) {
                return true;
            }
        }
        return false;
    }
}

















package com.grafysi.dynameta.core.plugin;

import com.grafysi.dynameta.core.loader.DynaObjectProvider;
import com.grafysi.dynameta.plugin.PluginDynamics;
import com.grafysi.dynameta.plugin.function.DynaFunction;
import io.grpc.BindableService;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.*;

public interface DynaRegistry {

    void register(DynaRegistryImpl.Plugin plugin);

    Set<DynaRegistryImpl.Plugin> getPlugins();

    DynaFunction<?, ?> getDynaFunction(
            String name, Object request, PluginDynamics.PluginSelector pluginSelector);

    List<BindableService> getGrpcServices();

    <T extends BindableService> List<? extends T> getGrpcServicesOfType(Class<T> serviceType);

    @EqualsAndHashCode
    @Getter
    class Plugin {

        private final String name;

        private final String parentName;

        private final boolean isTypeManager;

        private final String managedResourceType;

        private final Map<String, DynaObjectProvider<DynaFunction<?, ?>>> functionProviders;

        private final List<BindableService> grpcServices;

        public Plugin(Builder b) {
            this.name = Objects.requireNonNull(b.name);
            this.parentName = b.parentName;
            this.isTypeManager = b.isTypeManager;
            this.managedResourceType = b.managedResourceType;
            this.functionProviders = b.functionProviders;
            this.grpcServices = b.grpcServices;
        }

        DynaFunction<?, ?> getDynaFunction(String name) {
            var provider = functionProviders.get(name);
            if (provider != null) {
                return provider.getInstance();
            }
            return null;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {

            private String name;
            private String parentName;
            private boolean isTypeManager;
            private String managedResourceType;
            private final Map<String, DynaObjectProvider<DynaFunction<?, ?>>>
                    functionProviders = new HashMap<>();
            private final List<BindableService> grpcServices = new LinkedList<>();

            public Builder name(String name) {
                this.name = name;
                return this;
            }

            public Builder parentName(String parentName) {
                this.parentName = parentName;
                return this;
            }

            public Builder isTypeManager(boolean isTypeManager) {
                this.isTypeManager = isTypeManager;
                return this;
            }

            public Builder managedResourceType(String managedResourceType) {
                this.managedResourceType = managedResourceType;
                return this;
            }

            public Builder addFunctionProvider(String name, DynaObjectProvider<DynaFunction<?, ?>> provider) {
                functionProviders.put(name, provider);
                return this;
            }

            public Builder addFunctionProviders(Map<String,
                    DynaObjectProvider<DynaFunction<?, ?>>> functionProviders) {
                this.functionProviders.putAll(functionProviders);
                return this;
            }

            public Builder addGrpcService(BindableService grpcService) {
                grpcServices.add(grpcService);
                return this;
            }

            public Builder addGrpcServices(List<BindableService> grpcServices) {
                this.grpcServices.addAll(grpcServices);
                return this;
            }

            public Plugin build() {
                return new Plugin(this);
            }
        }
    }
}

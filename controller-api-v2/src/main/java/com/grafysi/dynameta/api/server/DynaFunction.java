package com.grafysi.dynameta.api.server;

import java.util.Map;

public interface DynaFunction<T, R> {

    String getName();

    boolean isRequired();

    FunctionHandler<T, R> getHandlerOf(Class<? extends FunctionModule> methodModule);

    boolean addHandlerOf(Class<? extends FunctionModule> methodModule, FunctionHandler<T, R> handler);

    boolean removeHandlerOf(Class<? extends FunctionModule> methodModule);

    class Builder<T, R> {

        private String name;
        private boolean isRequired;
        private Map<Class<? extends FunctionModule>, FunctionHandler<T, R>> handlers;

        public Builder<T, R> name(String name) {
            this.name = name;
            return this;
        }

        public Builder<T, R> isRequired(boolean isRequired) {
            this.isRequired = isRequired;
            return this;
        }

        public Builder<T, R> addHandler(Class<? extends FunctionModule> moduleClazz, FunctionHandler<T, R> handler) {
            handlers.put(moduleClazz, handler);
            return this;
        }

        public DynaFunction<T, R> build() {
            return new DynaFunctionImpl<>(name, isRequired, handlers);
        }
    }
}

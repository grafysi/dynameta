package com.grafysi.dynameta.plugin.function;

public class DefaultRequestWrapper<T> implements RequestWrapper<T> {

    private final T request;

    public DefaultRequestWrapper(T request) {
        this.request = request;
    }

    @Override
    public T getRequest() {
        return request;
    }

    public static<T> RequestWrapper<T> wrap(T request) {
        return new DefaultRequestWrapper<>(request);
    }
}

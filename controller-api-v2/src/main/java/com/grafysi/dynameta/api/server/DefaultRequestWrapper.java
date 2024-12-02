package com.grafysi.dynameta.api.server;

public class DefaultRequestWrapper<T> implements RequestWrapper<T> {

    private T wrapped;

    @Override
    public T getWrapped() {
        return wrapped;
    }
}

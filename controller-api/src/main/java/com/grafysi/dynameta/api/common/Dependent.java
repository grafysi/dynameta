package com.grafysi.dynameta.api.common;

public interface Dependent<T> {

    long getId();

    long getParentId();

    DependentType getType();

    T getPayload();
}

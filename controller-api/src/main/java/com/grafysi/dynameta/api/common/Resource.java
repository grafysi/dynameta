package com.grafysi.dynameta.api.common;

public interface Resource<T> {

    Long getId();

    String getName();

    Revision getRevision();

    Long getDefinitionId();

    T getValue();
}

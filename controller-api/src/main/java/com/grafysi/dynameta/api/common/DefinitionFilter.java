package com.grafysi.dynameta.api.common;

public interface DefinitionFilter {

    boolean test(String name);

    boolean test(int id);
}

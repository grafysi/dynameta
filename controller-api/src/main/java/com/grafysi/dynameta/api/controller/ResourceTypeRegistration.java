package com.grafysi.dynameta.api.controller;

import com.grafysi.dynameta.api.common.Definition;

public interface ResourceTypeRegistration {

    String getType();

    <S extends Definition, T> ResourceIOController<S, T> getResourceIOController();

    <S extends Definition, T> WatchController<S, T> getWatchController();
}

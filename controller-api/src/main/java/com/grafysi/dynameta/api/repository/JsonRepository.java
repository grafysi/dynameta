package com.grafysi.dynameta.api.repository;

import com.grafysi.dynameta.api.common.Dependent;

import java.util.List;

public interface JsonRepository {

    long createJson(long resourceId, Object json);

    void updateJson(long id, Object json);

    void deleteJson(long id);

    <T> Dependent<T> findJson(long id, Class<T> valueClass);

    <T> List<Dependent<T>> findJsons(long resourceId, Class<T> valueClass);
}

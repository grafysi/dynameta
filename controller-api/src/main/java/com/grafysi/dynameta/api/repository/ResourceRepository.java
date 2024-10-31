package com.grafysi.dynameta.api.repository;

import com.grafysi.dynameta.api.common.*;

import java.util.List;

public interface ResourceRepository {

    void createResource(String name, Long definitionId, Object value);

    void deleteResource(String name);

    void updateResource(String name, Object value);

    <T> Resource<T> findResource(String name, Revision revision, Class<T> valueClass);

    <T> List<Resource<T>> findResources(String nameStart, String nameEnd, Revisions revisions,
                                       DefinitionFilter definitionFilter, PageRequest pageRequest, Class<T> valueClass);
}

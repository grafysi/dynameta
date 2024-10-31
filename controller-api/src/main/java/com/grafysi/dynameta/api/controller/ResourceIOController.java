package com.grafysi.dynameta.api.controller;

import com.grafysi.dynameta.api.common.*;

import java.util.List;

public interface ResourceIOController<S extends Definition, T> extends Controller {

    void createResource(String name, S definition, T resData, List<Dependent<?>> dependents);

    void updateResource(String name, S definition, T resData);

    void deleteResource(String name, S definition);

    Resource<T> readResource(String name, Revision revision);

    void readResourceRange(String rangeStart, String rangeEnd, Revisions revisions,
                           DefinitionFilter definitionFilter, PageRequest pageRequest,
                           ReadOutput<Resource<T>> readOutput);

    void readDependents(String resourceName, Revision revision,
                        DependentType dependentType, ReadOutput<Dependent<?>> readOutput);
}

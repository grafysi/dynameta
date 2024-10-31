package com.grafysi.dynameta.repository;

import com.grafysi.dynameta.api.common.*;
import com.grafysi.dynameta.context.DynametaContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AbstractResourceRepositoryTest {

    private AbstractResourceRepository repository;

    @BeforeEach
    void setup() {
        repository = new AbstractResourceRepository() {
            @Override
            public void createResource(String name, Long definitionId, Object value) {

            }

            @Override
            public void deleteResource(String name) {

            }

            @Override
            public void updateResource(String name, Object value) {

            }

            @Override
            public <T> Resource<T> findResource(String name, Revision revision, Class<T> valueClass) {
                return null;
            }

            @Override
            public <T> List<Resource<T>> findResources(String nameStart, String nameEnd,
                                                       Revisions revisions,
                                                       DefinitionFilter definitionFilter,
                                                       PageRequest pageRequest, Class<T> valueClass) {
                return List.of();
            }
        };
        DynametaContext.loadDependencies(repository, repository.getClass());
    }

    @Test
    void baseCreateResource() {
        assertNotNull(repository.getDataSource());
        repository.baseCreateResource("dummy_resource", 1L,
                "{}".getBytes(StandardCharsets.UTF_8), null);
    }
}


























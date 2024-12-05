package com.grafysi.dynameta.core.dummyplugin;

import com.grafysi.dynameta.core.plugin.DynaRegistry;
import com.grafysi.dynameta.core.server.DummyStreamObserver;
import com.grafysi.dynameta.dummyplugin.CreateRequest;
import com.grafysi.dynameta.dummyplugin.DummyObject;
import com.grafysi.dynameta.dummyplugin.DummyPlugin;
import com.grafysi.dynameta.dummyplugin.DummyStore;
import com.grafysi.dynameta.dummyplugin.services.DummyService;
import com.grafysi.dynameta.plugin.function.ResponseObserver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(classes = DummyPluginTestConfig.class)
public class DummyServiceTest {

    @Autowired
    private DynaRegistry dynaRegistry;

    @Autowired
    private DummyStore dummyStore;

    private DummyService dummyService;

    @BeforeEach
    void setup() {
        assertEquals(1, dynaRegistry.getGrpcServices().size());
        dummyService = dynaRegistry
                .getGrpcServicesOfType(DummyService.class)
                .getFirst();
        assertNotNull(dummyService);
    }

    @AfterEach
    void tearDown() {
        dummyStore.reset();
    }

    @Test
    void dummyCreate() {
        var request = CreateRequest.newBuilder()
                .setFooValue("foo1")
                .setBarValue("bar1")
                .build();
        var dummyObserver = new DummyStreamObserver<DummyObject>();
        dummyService.create(request, ResponseObserver.wrap(dummyObserver));
        assertEquals(1, dummyObserver.getValues().size());
        assertEquals(1, dummyStore.count());
    }
}


























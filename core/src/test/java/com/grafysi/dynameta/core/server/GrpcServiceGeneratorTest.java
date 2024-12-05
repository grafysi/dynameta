package com.grafysi.dynameta.core.server;

import com.grafysi.dynameta.core.config.GrpcServiceGeneratorConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = GrpcServiceGeneratorConfig.class)
public class GrpcServiceGeneratorTest {

    @Autowired
    private GrpcServiceGenerator generator;

    @Autowired
    private SimpleGrpcMethodTracer grpcMethodTracer;

    @Test
    void loadGenerator() {
        assertNotNull(generator);
    }

    @Test
    void generateGrpcService() {
        var service = generator.from(DummyBindableService.class);
        assertNotNull(service);
        service.create(null, null);
        service.get(null, null);
        assertEquals(2, grpcMethodTracer.getCallList().size());
        assertEquals("DUMMY_CREATE", grpcMethodTracer.getCallList().get(0).getMethodName());
        assertEquals("DUMMY_GET", grpcMethodTracer.getCallList().get(1).getMethodName());
    }
}
































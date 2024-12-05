package com.grafysi.dynameta.core.config;

import com.grafysi.dynameta.core.server.DummyGrpcMethodAdapter;
import com.grafysi.dynameta.core.server.GrpcMethodAdapter;
import com.grafysi.dynameta.core.server.GrpcMethodTracer;
import com.grafysi.dynameta.core.server.SimpleGrpcMethodTracer;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

//@Configuration
//@ComponentScan(basePackages = {"com.grafysi.dynameta.core"})
public class GrpcServiceGeneratorConfig {

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public GrpcMethodAdapter<?, ?> grpcMethodAdapter(String methodName) {
        var adapter = new DummyGrpcMethodAdapter<>(methodName);
        adapter.setTracer(grpcMethodTracer());
        return adapter;
    }

    @Bean
    public GrpcMethodTracer grpcMethodTracer() {
        return new SimpleGrpcMethodTracer();
    }
}

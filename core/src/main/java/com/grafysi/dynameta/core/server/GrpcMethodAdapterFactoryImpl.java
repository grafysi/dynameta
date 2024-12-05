package com.grafysi.dynameta.core.server;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GrpcMethodAdapterFactoryImpl implements GrpcMethodAdapterFactory {

    private final GrpcMethodRunner grpcMethodRunner;

    @Autowired
    public GrpcMethodAdapterFactoryImpl(ObjectProvider<GrpcMethodAdapter<?,?>> provider, GrpcMethodRunner grpcMethodRunner) {
        this.grpcMethodRunner = Objects.requireNonNull(grpcMethodRunner);
    }

    @Override
    public GrpcMethodAdapter<?, ?> newInstance(String methodName) {
        return new GrpcMethodAdapterImpl<>(methodName, grpcMethodRunner);
    }
}

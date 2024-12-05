package com.grafysi.dynameta.core.server;

import io.grpc.stub.StreamObserver;

import java.util.Objects;

public class GrpcMethodAdapterImpl<T, R> implements GrpcMethodAdapter<T, R> {

    private final GrpcMethodRunner runner;

    private final String methodName;

    public GrpcMethodAdapterImpl(String methodName, GrpcMethodRunner runner) {
        this.methodName = Objects.requireNonNull(methodName);
        this.runner = Objects.requireNonNull(runner);
    }

    @Override
    public void handle(T request, StreamObserver<R> responseObserver) {
        runner.run(methodName, request, responseObserver);
    }
}

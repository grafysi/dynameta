package com.grafysi.dynameta.core.server;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DummyGrpcMethodAdapter<T, R> implements GrpcMethodAdapter<T, R> {

    private static final Logger logger = LoggerFactory.getLogger(DummyGrpcMethodAdapter.class);

    private String methodName;

    private GrpcMethodTracer tracer;

    public DummyGrpcMethodAdapter(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public void handle(T request, StreamObserver<R> responseObserver) {
        logger.info("Handling method: {}", methodName);
        tracer.trace(methodName, request, (StreamObserver<Object>) responseObserver);
    }

    public void setTracer(GrpcMethodTracer tracer) {
        this.tracer = tracer;
    }
}



















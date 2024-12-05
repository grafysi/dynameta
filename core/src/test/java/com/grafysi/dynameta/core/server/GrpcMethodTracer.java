package com.grafysi.dynameta.core.server;

import io.grpc.stub.StreamObserver;


public interface GrpcMethodTracer {

    void trace(String methodName, Object request, StreamObserver<Object> responseObserver);
}

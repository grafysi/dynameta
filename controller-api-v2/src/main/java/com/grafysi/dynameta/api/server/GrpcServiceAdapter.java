package com.grafysi.dynameta.api.server;

import io.grpc.stub.StreamObserver;

public interface GrpcServiceAdapter {

    <T, R> void handleMethod(String methodName, T request, StreamObserver<R> responseObserver);
}

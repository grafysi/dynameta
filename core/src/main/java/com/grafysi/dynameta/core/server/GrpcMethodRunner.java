package com.grafysi.dynameta.core.server;

import io.grpc.stub.StreamObserver;

public interface GrpcMethodRunner {

    <T, R> void run(String methodName, T request, StreamObserver<R> responseObserver);
}

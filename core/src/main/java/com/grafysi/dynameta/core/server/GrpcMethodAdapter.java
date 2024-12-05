package com.grafysi.dynameta.core.server;

import io.grpc.stub.StreamObserver;

public interface GrpcMethodAdapter<T, R> {

    void handle(T request, StreamObserver<R> responseObserver);
}

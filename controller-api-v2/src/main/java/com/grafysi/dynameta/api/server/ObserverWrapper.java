package com.grafysi.dynameta.api.server;

import io.grpc.stub.StreamObserver;

public interface ObserverWrapper<R> {

    StreamObserver<R> getWrapped();
}

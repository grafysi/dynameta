package com.grafysi.dynameta.plugin.function;

import io.grpc.stub.StreamObserver;

public interface ObserverWrapper<R> {

    StreamObserver<R> getObserver();
}

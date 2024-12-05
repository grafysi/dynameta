package com.grafysi.dynameta.plugin.function;

import io.grpc.stub.StreamObserver;

public class DefaultObserverWrapper<R> implements ObserverWrapper<R> {

    private final StreamObserver<R> responseObserver;

    public DefaultObserverWrapper(StreamObserver<R> responseObserver) {
        this.responseObserver = responseObserver;
    }

    @Override
    public StreamObserver<R> getObserver() {
        return responseObserver;
    }

    public static<R> ObserverWrapper<R> wrap(StreamObserver<R> responseObserver) {
        return new DefaultObserverWrapper<>(responseObserver);
    }

}

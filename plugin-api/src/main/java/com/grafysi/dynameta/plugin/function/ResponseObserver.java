package com.grafysi.dynameta.plugin.function;

import io.grpc.stub.StreamObserver;

public class ResponseObserver<R> implements StreamObserver<R> {

    private final StreamObserver<R> delegate;

    private ResponseObserver(StreamObserver<R> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void onCompleted() {
        delegate.onCompleted();
    }

    @Override
    public void onNext(R response) {
        delegate.onNext(response);
    }

    @Override
    public void onError(Throwable errorCause) {
        delegate.onError(errorCause);
    }

    public static<R> ResponseObserver<R> wrap(StreamObserver<R> internal) {
        return new ResponseObserver<>(internal);
    }
}

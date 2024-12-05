package com.grafysi.dynameta.core.server;

import io.grpc.stub.StreamObserver;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class DummyStreamObserver<V> implements StreamObserver<V> {

    private boolean isCompleted = false;

    private Throwable errorCause = null;

    private List<V> values = new LinkedList<>();

    @Override
    public void onCompleted() {
        this.isCompleted = true;
    }

    @Override
    public void onError(Throwable cause) {
        this.errorCause = cause;
    }

    @Override
    public void onNext(V value) {
        values.add(value);
    }

    public boolean isCompleted() {
        return isCompleted;
    }
}




























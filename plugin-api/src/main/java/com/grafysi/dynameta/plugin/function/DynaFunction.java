package com.grafysi.dynameta.plugin.function;

@FunctionalInterface
public interface DynaFunction<T, R> {

    void perform(T requestWrapper, ResponseObserver<R> responseObserver);

}

package com.grafysi.dynameta.api.server;

@FunctionalInterface
public interface FunctionHandler<RW extends RequestWrapper<?>, OW extends ObserverWrapper<?>> {

    void handle(RW requestWrapper, OW observer);

}

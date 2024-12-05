package com.grafysi.dynameta.core.server;

public interface GrpcMethodAdapterFactory {

    public GrpcMethodAdapter<?, ?> newInstance(String methodName);
}

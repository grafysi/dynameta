package com.grafysi.dynameta.core.server;

import com.google.protobuf.Struct;
import com.grafysi.dynameta.plugin.annotation.GrpcMethod;
import io.grpc.BindableService;
import io.grpc.ServerServiceDefinition;
import io.grpc.stub.StreamObserver;

public abstract class DummyBindableService implements BindableService {

    // ignore this method
    @Override
    public ServerServiceDefinition bindService() {
        return null;
    }

    @GrpcMethod(name = "DUMMY_CREATE")
    public abstract void create(Struct request, StreamObserver<Struct> responseObserver);

    @GrpcMethod(name = "DUMMY_GET")
    public abstract void get(Struct request, StreamObserver<Struct> responseObserver);

    public abstract void dummy1(Struct request, StreamObserver<Struct> responseObserver);

    @GrpcMethod(name = "DUMMY_DUMMY2")
    public void dummy2(Struct request, StreamObserver<Struct> responseObserver) {
        // this method should not be called
        throw new RuntimeException();
    }
}

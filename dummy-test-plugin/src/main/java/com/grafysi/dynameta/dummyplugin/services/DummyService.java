package com.grafysi.dynameta.dummyplugin.services;

import com.grafysi.dynameta.dummyplugin.*;
import com.grafysi.dynameta.plugin.annotation.Context;
import com.grafysi.dynameta.plugin.annotation.GrpcMethod;
import com.grafysi.dynameta.plugin.annotation.GrpcService;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

@GrpcService
public abstract class DummyService extends DummyServiceGrpc.DummyServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(DummyService.class);

    private static final AtomicInteger idCount = new AtomicInteger(0);

    @Context
    private DummyStore dummyStore;

    @Override
    @GrpcMethod(name = DummyPlugin.DUMMY_CREATE)
    public abstract void create(CreateRequest request, StreamObserver<DummyObject> responseObserver);

    @Override
    @GrpcMethod(name = DummyPlugin.DUMMY_GET)
    public void get(GetRequest request, StreamObserver<DummyObject> responseObserver) {
        logger.info("DummyService.Get called");
        var dummyObj = findDummyObject(request.getId());
        responseObserver.onNext(dummyObj);
        responseObserver.onCompleted();
    }

    public boolean allDependenciesLoaded() {
        return dummyStore != null;
    }

    private int generateId() {
        return idCount.incrementAndGet();
    }

    private DummyObject createDummyObject(String foo, String bar) {
        return dummyStore.create(foo, bar);
    }

    private DummyObject findDummyObject(Integer id) {
        return dummyStore.find(id);
    }
}

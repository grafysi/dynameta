package com.grafysi.dynameta.baseplugin.services;

import com.grafysi.dynameta.baseplugin.*;
import com.grafysi.dynameta.plugin.annotation.GrpcMethod;
import com.grafysi.dynameta.plugin.annotation.GrpcService;
import io.grpc.stub.StreamObserver;

import static com.grafysi.dynameta.baseplugin.BasePlugin.*;

@GrpcService
public abstract class ResourceDefinitionService extends ResourceDefinitionGrpc.ResourceDefinitionImplBase {

    @Override
    @GrpcMethod(name = CREATE_RESOURCE_DEFINITION)
    public abstract void create(CreateRequest request, StreamObserver<CreateResponse> responseObserver);

    @Override
    @GrpcMethod(name = GET_RESOURCE_DEFINITION)
    public abstract void get(GetRequest request, StreamObserver<ResourceDefinition> responseObserver);

    @Override
    @GrpcMethod(name = LIST_RESOURCE_DEFINITION)
    public abstract void list(ListRequest request, StreamObserver<ResourceDefinition> responseObserver);
}

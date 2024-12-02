package com.grafysi.dynameta.baseplugin;

import com.grafysi.dynameta.api.annotation.Context;
import com.grafysi.dynameta.api.annotation.GrpcMethod;
import com.grafysi.dynameta.api.annotation.GrpcService;
import com.grafysi.dynameta.api.server.GrpcServiceAdapter;
import io.grpc.stub.StreamObserver;

import static com.grafysi.dynameta.baseplugin.BasePluginFunctions.*;

@GrpcService
public abstract class ResourceDefinitionService extends ResourceDefinitionGrpc.ResourceDefinitionImplBase {

    @Override
    @GrpcMethod(name = CREATE_RESOURCE_DEFINITION, required = true)
    public abstract void create(CreateRequest request, StreamObserver<CreateResponse> responseObserver);

    @Override
    @GrpcMethod(name = GET_RESOURCE_DEFINITION, required = true)
    public abstract void get(GetRequest request, StreamObserver<ResourceDefinition> responseObserver);

    @Override
    @GrpcMethod(name = LIST_RESOURCE_DEFINITION, required = true)
    public abstract void list(ListRequest request, StreamObserver<ResourceDefinition> responseObserver);
}

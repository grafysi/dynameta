package com.grafysi.dynameta.server.service;

import com.google.protobuf.Empty;
import com.grafysi.dynameta.datastore.resource.Resource;
import com.grafysi.dynameta.datastore.resource.ResourceStore;
import com.grafysi.dynameta.server.CreateResourceData;
import com.grafysi.dynameta.server.ResourceCRUDGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResourceCRUDService extends ResourceCRUDGrpc.ResourceCRUDImplBase {

    private final ResourceStore resourceStore;

    public ResourceCRUDService(@Autowired ResourceStore resourceStore) {
        this.resourceStore = resourceStore;
    }

    @Override
    public void createResource(CreateResourceData request, StreamObserver<Empty> respondObserver) {

        var resource = Resource.builder()
                .name(request.getName())
                .definitionId(request.getDefinitionId())
                .data(request.getData().toByteArray())
                .build();

        resourceStore.persist(resource);

        respondObserver.onNext(Empty.getDefaultInstance());
        respondObserver.onCompleted();
    }
}




















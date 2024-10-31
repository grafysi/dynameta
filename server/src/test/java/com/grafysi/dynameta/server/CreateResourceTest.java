package com.grafysi.dynameta.server;

import com.google.protobuf.ByteString;
import com.grafysi.dynameta.datastore.resource.Resource;
import io.grpc.Channel;
import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateResourceTest {

    private static final Logger logger = LoggerFactory.getLogger(CreateResourceTest.class);

    private static final String TARGET = "localhost:8889";

    private ResourceCRUDGrpc.ResourceCRUDBlockingStub blockingStub;

    private ResourceCRUDGrpc.ResourceCRUDStub asyncStub;

    private ManagedChannel channel;

    @BeforeEach
    public void setup() {
        channel = Grpc.newChannelBuilder(TARGET, InsecureChannelCredentials.create())
                .build();
        blockingStub = ResourceCRUDGrpc.newBlockingStub(channel);
        asyncStub = ResourceCRUDGrpc.newStub(channel);
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        channel.shutdown();
        if (!channel.awaitTermination(20, TimeUnit.SECONDS)) {
            logger.error("Channel termination timeout");
        }
    }

    @Test
    public void createResource() {

        var request = CreateResourceData.newBuilder()
                .setName("dummy/resource")
                .setDefinitionId(111)
                .setData(ByteString.copyFrom("{}", StandardCharsets.UTF_8))
                .build();

        var empty = blockingStub.createResource(request);
        assertNotNull(empty);
    }

}




























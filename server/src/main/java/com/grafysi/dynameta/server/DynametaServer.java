package com.grafysi.dynameta.server;

import com.grafysi.dynameta.server.service.ResourceCRUDService;
import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class DynametaServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynametaServer.class);

    private final int port;

    private final Server server;

    public DynametaServer(@Value("${server.port}") int port,
                          @Autowired ResourceCRUDService resourceCRUDService) {
        this.port = port;
        this.server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
                .addService(resourceCRUDService)
                .build();
    }

    public void start() throws IOException {
        server.start();
        LOGGER.info("Server started, listening on port {}", port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                LOGGER.error("*** Shutting down gRPC server since JVM is shutting down");
                try {
                    DynametaServer.this.stop();
                } catch (InterruptedException e) {
                    LOGGER.error("Interrupted while shutting down", e);
                }
                LOGGER.error("*** Server shut down");
            }
        });
    }

    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }




}






























package com.grafysi.dynameta.server;

import com.grafysi.dynameta.server.config.SpringConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException, InterruptedException {

        var context = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        var server = context.getBean(DynametaServer.class);

        server.start();
        server.blockUntilShutdown();
    }
}

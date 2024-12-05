package com.grafysi.dynameta.core.server;

import com.google.protobuf.Message;
import com.grafysi.dynameta.core.plugin.DynaRegistry;
import com.grafysi.dynameta.plugin.PluginDynamics;
import com.grafysi.dynameta.plugin.function.DefaultRequestWrapper;
import com.grafysi.dynameta.plugin.function.DynaFunction;
import com.grafysi.dynameta.plugin.function.RequestWrapper;
import com.grafysi.dynameta.plugin.function.ResponseObserver;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GrpcMethodRunnerImpl implements GrpcMethodRunner {

    private final DynaRegistry dynaRegistry;

    @Autowired
    public GrpcMethodRunnerImpl(DynaRegistry dynaRegistry) {
        this.dynaRegistry = Objects.requireNonNull(dynaRegistry);
    }

    /*@Override
    public<T, R> void run(String grpcMethod, T request, StreamObserver<R> responseObserver) {
        var function = dynaRegistry.getDynaFunction(
                grpcMethod,
                extractPluginSelector((Message) request));
        var requestWrapper = DefaultRequestWrapper.wrap(request);
        function.perform((DefaultRequestWrapper<?>) DefaultRequestWrapper.wrap(request), (DefaultObserverWrapper<?>) DefaultObserverWrapper.wrap(responseObserver));
    }*/

    @Override
    public<T, R> void run(String grpcMethod, T request, StreamObserver<R> responseObserver) {
        var function = (DynaFunction<T, R>) dynaRegistry.getDynaFunction(
                grpcMethod, request,
                extractPluginSelector((Message) request));

        function.perform(request, ResponseObserver.wrap(responseObserver));
    }

    private<T> RequestWrapper<T> wrapRequest(T request) {
        return new DefaultRequestWrapper<>(request);
    }


    private PluginDynamics.PluginSelector extractPluginSelector(Message request) {
        return request.getDescriptorForType()
                .getOptions()
                .getExtension(PluginDynamics.pluginSelector);
    }
}



































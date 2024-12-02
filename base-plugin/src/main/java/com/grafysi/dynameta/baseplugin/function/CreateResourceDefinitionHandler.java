package com.grafysi.dynameta.baseplugin.function;

import com.grafysi.dynameta.api.annotation.FunctionConfiguration;
import com.grafysi.dynameta.api.server.*;
import com.grafysi.dynameta.baseplugin.BasePluginFunctions;
import com.grafysi.dynameta.baseplugin.CreateRequest;
import com.grafysi.dynameta.baseplugin.CreateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FunctionConfiguration(name = BasePluginFunctions.CREATE_RESOURCE_DEFINITION)
public class CreateResourceDefinitionHandler
        implements FunctionHandler<
        ResDefRequestWrapper<CreateRequest>,
        ObserverWrapper<CreateResponse>> {

    private static final Logger logger = LoggerFactory.getLogger(CreateResourceDefinitionHandler.class);

    @Override
    public void handle(ResDefRequestWrapper<CreateRequest> reqWrapper,
                       ObserverWrapper<CreateResponse> obsWrapper) {
        var observer = obsWrapper.getWrapped();
        var response = CreateResponse.newBuilder()
                .setId(Integer.MAX_VALUE)
                .build();
        observer.onNext(response);
        observer.onCompleted();
        logger.info("Handled create_resource_definition...");
    }
}





















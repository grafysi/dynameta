package com.grafysi.dynameta.baseplugin.functions;

import com.grafysi.dynameta.baseplugin.BasePlugin;
import com.grafysi.dynameta.plugin.annotation.FunctionConfiguration;
import com.grafysi.dynameta.plugin.annotation.Sharable;
import com.grafysi.dynameta.baseplugin.CreateRequest;
import com.grafysi.dynameta.baseplugin.CreateResponse;
import com.grafysi.dynameta.plugin.function.DynaFunction;
import com.grafysi.dynameta.plugin.function.ResponseObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Sharable
@FunctionConfiguration(name = BasePlugin.CREATE_RESOURCE_DEFINITION)
public class CreateResourceDefinition
        implements DynaFunction<CreateRequest, CreateResponse> {

    private static final Logger logger = LoggerFactory.getLogger(CreateResourceDefinition.class);

    @Override
    public void perform(CreateRequest request, ResponseObserver<CreateResponse> observer) {
        var response = CreateResponse.newBuilder()
                .setId(Integer.MAX_VALUE)
                .build();
        observer.onNext(response);
        observer.onCompleted();
        logger.info("Handled create_resource_definition...");
    }
}





















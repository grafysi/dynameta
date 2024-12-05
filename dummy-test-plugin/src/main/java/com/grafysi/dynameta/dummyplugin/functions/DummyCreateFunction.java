package com.grafysi.dynameta.dummyplugin.functions;

import com.grafysi.dynameta.dummyplugin.CreateRequest;
import com.grafysi.dynameta.dummyplugin.DummyObject;
import com.grafysi.dynameta.dummyplugin.DummyPlugin;
import com.grafysi.dynameta.dummyplugin.DummyStore;
import com.grafysi.dynameta.plugin.annotation.Context;
import com.grafysi.dynameta.plugin.annotation.FunctionConfiguration;
import com.grafysi.dynameta.plugin.annotation.Sharable;
import com.grafysi.dynameta.plugin.function.DynaFunction;
import com.grafysi.dynameta.plugin.function.ResponseObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Sharable
@FunctionConfiguration(name = DummyPlugin.DUMMY_CREATE)
public class DummyCreateFunction implements DynaFunction<CreateRequest, DummyObject> {

    private static final Logger logger = LoggerFactory.getLogger(DummyCreateFunction.class);

    @Context
    private DummyStore dummyStore;

    @Override
    public void perform(CreateRequest request, ResponseObserver<DummyObject> responseObserver) {
        logger.info("Performing {}", DummyPlugin.DUMMY_CREATE);
        var createdObj = dummyStore.create(request.getFooValue(), request.getBarValue());
        responseObserver.onNext(createdObj);
        responseObserver.onCompleted();
    }
}































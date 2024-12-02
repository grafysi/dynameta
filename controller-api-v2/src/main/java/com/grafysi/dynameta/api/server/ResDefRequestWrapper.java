package com.grafysi.dynameta.api.server;

import com.grafysi.dynameta.api.annotation.Context;
import com.grafysi.dynameta.api.domain.ResourceDefinition;

public class ResDefRequestWrapper<T> extends DefaultRequestWrapper<T> {

    @Context
    private ResourceDefinition resDef;

    public ResourceDefinition getResDef() {
        return resDef;
    }
}

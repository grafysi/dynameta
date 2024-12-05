package com.grafysi.dynameta.plugin.function;

import com.grafysi.dynameta.plugin.annotation.Context;
import com.grafysi.dynameta.plugin.metadata.ResourceDef;

public class ResDefRequestWrapper<T> extends DefaultRequestWrapper<T> {

    @Context
    private ResourceDef resDef;

    public ResDefRequestWrapper(T request) {
        super(request);
    }

    public ResourceDef getResDef() {
        return resDef;
    }
}

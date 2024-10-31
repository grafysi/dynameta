package com.grafysi.dynameta.api.common;

public interface ReadOutput<O> {

    void next(O output);

    void completed();
}

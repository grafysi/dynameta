package com.grafysi.dynameta.api.common;

public interface PageRequest {

    enum SortOrder {NONE, ASCEND, DESCEND}

    long getLimit();

    SortOrder getSortOrder();

    String getSortTarget();
}

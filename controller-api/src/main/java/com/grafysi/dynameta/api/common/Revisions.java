package com.grafysi.dynameta.api.common;

public interface Revisions {

    int getVersionRecentLower();

    int getVersionRecentUpper();

    long getRevisionMin();

    long getRevisionMax();
}

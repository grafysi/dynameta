package com.grafysi.dynameta.datastore.resource;

public interface Resource {

    Integer getId();

    String getName();

    String getType();

    Integer getDefinitionId();

    byte[] getData();

    Integer getLeaseId();

    static ResourceImpl.ResourceImplBuilder builder() {
        return ResourceImpl.builder();
    }
}

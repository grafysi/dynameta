package com.grafysi.dynameta.datastore.resource;

import lombok.Builder;
import lombok.Getter;

@Builder
public class ResourceImpl implements Resource {

    @Getter
    private final Integer id;

    @Getter
    private final String name;

    @Getter
    private final String type;

    @Getter
    private final Integer definitionId;

    @Getter
    private final byte[] data;

    @Getter
    private final Integer leaseId;

    public ResourceImpl(Integer id, String name, String type,
                        Integer definitionId, byte[] data, Integer leaseId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.definitionId = definitionId;
        this.data = data;
        this.leaseId = leaseId;
    }
}
























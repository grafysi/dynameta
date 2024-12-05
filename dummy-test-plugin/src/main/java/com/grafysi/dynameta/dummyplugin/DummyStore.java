package com.grafysi.dynameta.dummyplugin;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DummyStore {

    private final HashMap<Integer, DummyObject> dummyMap = new HashMap<>();

    private static final AtomicInteger idCounter = new AtomicInteger(0);

    public DummyObject create(String fooValue, String barValue) {
        var dummyObj = DummyObject.newBuilder()
                .setId(generateId())
                .setFooValue(fooValue)
                .setBarValue(barValue)
                .build();
        dummyMap.put(dummyObj.getId(), dummyObj);
        return dummyObj;
    }

    public DummyObject find(Integer id) {
        return dummyMap.get(id);
    }

    private int generateId() {
        return idCounter.incrementAndGet();
    }

    public void reset() {
        idCounter.set(0);
        dummyMap.clear();
    }

    public int count() {
        return dummyMap.size();
    }
}

package com.grafysi.dynameta.core.loader;

import com.grafysi.dynameta.core.annotation.DependencyFieldResolver;
import com.grafysi.dynameta.core.exception.ObjectLoadException;
import com.grafysi.dynameta.plugin.annotation.Context;

public class DynaObjectProvider<T> {

    private final boolean objectSharable;

    private T sharedInstance;

    private boolean sharedInitialized;

    private final Class<T> objectClass;

    private final DependencyFieldResolver fieldResolver;

    public DynaObjectProvider(Class<T> objectClass, boolean objectSharable, DependencyFieldResolver fieldResolver) {
        this.objectSharable = objectSharable;
        this.objectClass = objectClass;
        this.fieldResolver = fieldResolver;
    }

    public T getInstance() {
        if (objectSharable && sharedInitialized) {
            return sharedInstance;
        }
        var createdInstance = createInstance();
        if (objectSharable) {
            sharedInitialized = true;
            this.sharedInstance = createdInstance;
        }
        return createdInstance;
    }

    private T createInstance() {
        if (objectSharable && sharedInitialized) {
            return sharedInstance;
        }
        try {
            var instance = objectClass.getConstructor().newInstance();
            resolveContextFields(instance.getClass(), instance);
            return instance;
        } catch (Exception e) {
            throw new ObjectLoadException(e);
        }
    }

    private<I> void resolveContextFields(Class<?> iClass, T instance) {
        fieldResolver.resolve(Context.class, (Class<? super T>) iClass, instance);
    }
}



























package com.grafysi.dynameta.context;

import com.grafysi.dynameta.api.annotation.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DynametaContext {

    private static final Logger logger = LoggerFactory.getLogger(DynametaContext.class);

    private static final DynametaContext singleton;

    static {
        singleton = new DynametaContext();
    }

    private final ApplicationContext context;

    private DynametaContext() {
        context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
    }

    private ApplicationContext appContext() {
        return context;
    }

    public static <T> T instantiate(Class<T> clazz) throws InstantiationException {
        try {
            // assume the clazz have no-arg constructor
            var ins = clazz.getDeclaredConstructor().newInstance();
            // inject dependencies
            loadDependencies(ins, ins.getClass());
            return ins;
        }
        catch (Exception e) {
            throw new InstantiationException(e.getMessage());
        }
    }

    public static void loadDependencies(Object obj, Class<?> clazz) {
        if (clazz.equals(Object.class)) {
            return;
        }
        try {
            for (var field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Inject.class)) {
                    var dep = singleton.appContext().getBean(field.getType());
                    field.setAccessible(true);
                    field.set(obj, dep);
                }
            }
        } catch (Exception e) {
            // just return object
            throw new RuntimeException(e);
        }
        loadDependencies(obj, clazz.getSuperclass());
    }

    private static <T> T getBean(Class<T> clazz) {
        try {
            return singleton.appContext().getBean(clazz);
        }
        catch (NoSuchBeanDefinitionException e) {
            // just ignore
        }
        catch (BeansException e) {
            // just ignore ...
        }
        return null;
    }
}




























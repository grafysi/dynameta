package com.grafysi.dynameta.core.annotation;

import com.grafysi.dynameta.core.exception.DynaInitializeException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Arrays;

@Component
public class DependencyFieldResolver implements ApplicationContextAware {

    private ApplicationContext appContext;

    @Override
    public void setApplicationContext(ApplicationContext appContext) {
        this.appContext = appContext;
    }

    public<T> void resolve(Class<? extends Annotation> annoClass, Class<T> tClass, T instance) {
        Arrays.stream(tClass.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(annoClass))
                .forEach(f -> {
                    try {
                        f.setAccessible(true);
                        f.set(instance, getDependency(f.getType()));
                    } catch (IllegalAccessException e) {
                        throw new DynaInitializeException();
                    }
                });
    }

    private<D> D getDependency(Class<D> dClass) {
        return appContext.getBean(dClass);
    }

}





























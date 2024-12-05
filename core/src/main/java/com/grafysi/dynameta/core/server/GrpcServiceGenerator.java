package com.grafysi.dynameta.core.server;

import com.grafysi.dynameta.core.annotation.DependencyFieldResolver;
import com.grafysi.dynameta.core.exception.GrpcServiceGenerateException;
import com.grafysi.dynameta.plugin.annotation.Context;
import com.grafysi.dynameta.plugin.annotation.GrpcMethod;
import io.grpc.BindableService;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static net.bytebuddy.matcher.ElementMatchers.hasMethodName;
import static net.bytebuddy.matcher.ElementMatchers.isAbstract;

@Component
public class GrpcServiceGenerator {

    private DependencyFieldResolver fieldResolver;

    private GrpcMethodAdapterFactory adapterFactory;

    @Autowired
    public GrpcServiceGenerator(DependencyFieldResolver fieldResolver, GrpcMethodAdapterFactory adapterFactory) {
        this.fieldResolver = fieldResolver;
        this.adapterFactory = adapterFactory;
    }

    public<T extends BindableService>  T from(Class<T> serviceClazz) {

        var serviceBuilder = new ByteBuddy()
                .subclass(serviceClazz);

        for (var m : serviceClazz.getMethods()) {
            if (m.isAnnotationPresent(GrpcMethod.class)) {
                var formalName = m.getAnnotation(GrpcMethod.class).name();
                var adapter = newAdapter(formalName);
                serviceBuilder = serviceBuilder.method(isAbstract().and(hasMethodName(m.getName())))
                        .intercept(MethodDelegation.to(adapter));
            }
        }
        try (var unloaded = serviceBuilder.make()) {
            var resultClazz = unloaded.load(ClassLoader.getSystemClassLoader())
                    .getLoaded();
            var serviceInstance = resultClazz.getConstructor().newInstance();
            resolveContextFields(serviceClazz, serviceInstance);
            return serviceInstance;
        } catch (Exception e) {
            throw new GrpcServiceGenerateException(e);
        }
    }

    private<T> void resolveContextFields(Class<T> tClass, T instance) {
        fieldResolver.resolve(Context.class, tClass, instance);
    }

    private GrpcMethodAdapter<?, ?> newAdapter(String methodName) {
        return adapterFactory.newInstance(methodName);
    }

}



























package com.grafysi.dynameta.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PluginConfiguration {
    String name();
    String extendedPlugin() default "";
    boolean isTypeManager();
    String managedResourceType() default "";
    String[] grpcServicePackages() default {};
    String[] functionHandlerPackages() default {};
}

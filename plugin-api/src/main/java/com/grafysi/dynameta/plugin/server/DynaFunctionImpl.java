//package com.grafysi.dynameta.api.server;
//
//import com.grafysi.dynameta.api.exception.MethodLoadException;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class DynaFunctionImpl<T, R> implements DynaFunction<T, R> {
//
//    private final String name;
//
//    private final boolean isRequired;
//
//    private final Map<Class<? extends FunctionModule>, FunctionHandler<T, R>> handlers;
//
//    private final Map<Class<? extends FunctionModule>, FunctionHandler<T, R>> moduleHandlerCache;
//
//    DynaFunctionImpl(String name, boolean isRequired,
//                     Map<Class<? extends FunctionModule>, FunctionHandler<T, R>> handlers) {
//        this.name = name;
//        this.isRequired = isRequired;
//        this.handlers = handlers;
//        this.moduleHandlerCache = new HashMap<>();
//    }
//
//    @Override
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public boolean isRequired() {
//        return isRequired;
//    }
//
//    @Override
//    public boolean addHandlerOf(Class<? extends FunctionModule> moduleClazz, FunctionHandler<T, R> handler) {
//        if (handlers.containsKey(moduleClazz)) {
//            return false;
//        }
//        handlers.put(moduleClazz, handler);
//        return true;
//    }
//
//    @Override
//    public boolean removeHandlerOf(Class<? extends FunctionModule> moduleClazz) {
//        if (handlers.containsKey(moduleClazz)) {
//            handlers.remove(moduleClazz);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public FunctionHandler<T, R> getHandlerOf(Class<? extends FunctionModule> moduleClazz) {
//        var handler = handlers.get(moduleClazz);
//        if (handler != null) return handler;
//
//        var fallbackAnno = moduleClazz.getAnnotation(FallbackFunctionModule.class);
//        if (fallbackAnno != null) {
//            try {
//                var fallbackClass = Class.forName(fallbackAnno.className());
//                if (FunctionModule.class.isAssignableFrom(fallbackClass)) {
//                    return getHandlerOf((Class<? extends FunctionModule>) fallbackClass);
//                }
//                else {
//                    throw new MethodLoadException(
//                            "Specified fallback class is not of type " + FunctionModule.class);
//                }
//            }
//            catch (ClassNotFoundException e) {
//                throw new MethodLoadException("Specified fallback class not found");
//            }
//        }
//        return null;
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

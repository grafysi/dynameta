package com.grafysi.dynameta.api.exception;

public class MethodLoadException extends RuntimeException {

    public MethodLoadException(String message) {
        super(message);
    }

    public MethodLoadException(Throwable cause) {
        super(cause);
    }

    public MethodLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public MethodLoadException() {
        super();
    }
}

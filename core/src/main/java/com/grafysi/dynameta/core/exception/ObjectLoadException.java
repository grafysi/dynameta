package com.grafysi.dynameta.core.exception;

public class ObjectLoadException extends RuntimeException {

  public ObjectLoadException(String message) {
      super(message);
  }

  public ObjectLoadException(String message, Throwable cause) {
      super(message, cause);
  }

  public ObjectLoadException(Throwable cause) {
      super(cause);
  }

  public ObjectLoadException() {
      super();
  }
}

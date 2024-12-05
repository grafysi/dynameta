package com.grafysi.dynameta.core.exception;

public class DynaInitializeException extends RuntimeException {

  public DynaInitializeException(String message) {
      super(message);
  }

  public DynaInitializeException(String message, Throwable cause) {
      super(message, cause);
  }

  public DynaInitializeException(Throwable cause) {
      super(cause);
  }

  public DynaInitializeException() {
      super();
  }
}

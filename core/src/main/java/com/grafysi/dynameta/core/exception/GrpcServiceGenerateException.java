package com.grafysi.dynameta.core.exception;

public class GrpcServiceGenerateException extends RuntimeException {

  public GrpcServiceGenerateException(String message) {
      super(message);
  }

  public GrpcServiceGenerateException(String message, Throwable cause) {
      super(message, cause);
  }

  public GrpcServiceGenerateException(Throwable cause) {
      super(cause);
  }

  public GrpcServiceGenerateException() {
      super();
  }
}

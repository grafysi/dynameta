package com.grafysi.dynameta.core.exception;

public class RegisteringException extends RuntimeException {

  public RegisteringException(String message) {
      super(message);
  }

  public RegisteringException(String message, Throwable cause) {
      super(message, cause);
  }

  public RegisteringException(Throwable cause) {
      super(cause);
  }

  public RegisteringException() {
      super();
  }
}

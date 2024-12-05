package com.grafysi.dynameta.core.exception;

public class PluginResolveException extends RuntimeException {

  public PluginResolveException(String message) {
      super(message);
  }

  public PluginResolveException(String message, Throwable cause) {
      super(message, cause);
  }

  public PluginResolveException(Throwable cause) {
      super(cause);
  }

  public PluginResolveException() {
      super();
  }
}

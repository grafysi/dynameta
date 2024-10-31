package com.grafysi.dynameta.api.repository;

public class RepositoryException extends RuntimeException {

    public RepositoryException() {
        super();
    }

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(Throwable cause) {
        super(cause);
    }

    public RepositoryException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

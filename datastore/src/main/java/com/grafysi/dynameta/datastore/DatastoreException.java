package com.grafysi.dynameta.datastore;

public class DatastoreException extends RuntimeException {

    public DatastoreException(Throwable cause) {
        super(cause);
    }

    public DatastoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatastoreException(String message) {
        super(message);
    }

    public DatastoreException() {
        super();
    }
}

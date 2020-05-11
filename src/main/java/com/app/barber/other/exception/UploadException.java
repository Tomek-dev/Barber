package com.app.barber.other.exception;

public class UploadException extends RuntimeException {
    public UploadException() {
        super("Upload exception");
    }

    public UploadException(String message) {
        super(message);
    }

    public UploadException(Throwable cause) {
        super(cause);
    }
}

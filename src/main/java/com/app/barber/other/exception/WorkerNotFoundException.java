package com.app.barber.other.exception;

public class WorkerNotFoundException extends RuntimeException{
    public WorkerNotFoundException() {
        super("Worker not found");
    }

    public WorkerNotFoundException(String message) {
        super(message);
    }

    public WorkerNotFoundException(Throwable cause) {
        super(cause);
    }
}

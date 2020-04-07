package com.app.barber.other.exception;

public class EnumNotExistException extends RuntimeException{
    public EnumNotExistException() {
        super("Enum not exist");
    }

    public EnumNotExistException(String message) {
        super(message);
    }

    public EnumNotExistException(Throwable cause) {
        super(cause);
    }
}

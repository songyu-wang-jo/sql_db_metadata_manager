package com.songyuwong.sqldbmetadatamanager.exception;

public class ConnectionException extends Exception{

    private final String message;

    public ConnectionException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}

package com.bms.gymmanagementsystem.exception;

public class ClientAlreadyExistException extends RuntimeException{
    public ClientAlreadyExistException(String message) {
        super(message);
    }
}

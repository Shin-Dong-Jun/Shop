package com.example.bravobra.exception;

public class InvalidLoginCredentialException extends RuntimeException {

    public InvalidLoginCredentialException(String message) {
        super(message);
    }
}

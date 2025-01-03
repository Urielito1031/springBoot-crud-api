package com.firstapi.mi_primer_api_rest.Exceptions;

public class UserDuplicatedException extends RuntimeException {
    public UserDuplicatedException(String message) {
        super(message);
    }

}

package com.home.todoList.common.utilities.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Created by andrew on 02.01.16.
 */
public class SuperException extends Exception {
    private String message;
    private HttpStatus httpStatus;

    SuperException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public SuperException(String message) {
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = message;
    }
}

package com.brenosalles.reqres.http;

public class UnauthorizedException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
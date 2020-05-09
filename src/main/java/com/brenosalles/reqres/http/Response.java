package com.brenosalles.reqres.http;

public class Response {
    // Attributes
    private Integer statusCode;
    private String body;

    // Constructor
    public Response(Integer statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    // Getters & Setters
    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
package com.brenosalles.reqres.http;

import java.util.ArrayList;

public class Response {
    // Attributes
    private Integer statusCode;
    private String body;
    private ArrayList<String> contentTypes;

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

    public ArrayList<String> getContentTypes() {
        return contentTypes;
    }

    public void setContentTypes(ArrayList<String> contentType) {
        this.contentTypes = contentType;
    }
}
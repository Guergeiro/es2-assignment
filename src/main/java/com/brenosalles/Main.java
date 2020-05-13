package com.brenosalles;

import com.brenosalles.reqres.http.HttpMethods;
import com.brenosalles.reqres.http.Request;
import com.brenosalles.reqres.http.Response;

public class Main {
    public static void main(String args[]) {
        Response res = Request.makeHttpRequest("https://reqres.in/api/register", HttpMethods.POST,
                "{\"email\":\"ola@123.com\"}");
    }
}
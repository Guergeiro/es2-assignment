package com.brenosalles.reqres.handlers;

import com.brenosalles.reqres.http.HttpMethods;
import com.brenosalles.reqres.http.Response;

public interface IHandler {
    IHandler setHandler(IHandler handler);

    Response handle(Object obj, HttpMethods method);
}
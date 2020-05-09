package com.brenosalles.handlers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface IHandler {
    IHandler setHandler(IHandler handler);

    void handle(JSONObject obj);

    void handle(JSONArray arr);
}
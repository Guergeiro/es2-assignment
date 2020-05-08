package com.brenosalles.reqres.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;

public abstract class Request {
    public static String makeHttpRequest(URL url, HttpMethods method, JSONObject body) throws Exception {

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod(method.name());
        switch (method.name()) {
            case "GET":
            case "DELETE":
                break;
            case "POST":
            case "PUT":
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(body.toJSONString());
                wr.close();
                break;
            default:
                throw new Exception("Method not implemented");
        }

        InputStream is = conn.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }
}
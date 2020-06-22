package com.brenosalles.reqres.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Request {
    public static Response makeHttpRequest(String url, HttpMethods method, String body) {
        StringBuilder result = new StringBuilder();
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
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
                    wr.writeBytes(body);
                    wr.close();
                    break;
                default:
                    /** Intentionally empty */
            }

            if (conn.getResponseCode() >= 400) {
                InputStream is = conn.getErrorStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                rd.close();
            } else {
                InputStream is = conn.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                rd.close();

            }
            Response response = new Response();
            response.setStatusCode(conn.getResponseCode());
            response.setBody(result.toString());
            if (conn.getContentType() != null) {
                ArrayList<String> contentTypes = new ArrayList<String>(
                        Arrays.asList(conn.getContentType().trim().split(";")));
                response.setContentTypes(contentTypes);
            }
            return response;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        Response response = new Response();
        response.setStatusCode(500);
        response.setBody("{\"error\":\"Internal Server Error\"}");
        ArrayList<String> contentTypes = new ArrayList<String>(Arrays.asList("application/json"));
        response.setContentTypes(contentTypes);
        return response;
    }
}
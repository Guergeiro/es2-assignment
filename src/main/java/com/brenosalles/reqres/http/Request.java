package com.brenosalles.reqres.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
            return new Response(conn.getResponseCode(), result.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return new Response(500, "Internal server error");
    }
}
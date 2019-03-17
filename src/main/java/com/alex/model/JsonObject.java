package com.alex.model;

import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class JsonObject {
    public static JSONObject getJsonObject(HttpServletRequest req) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = req.getReader();
        String str;
        while( (str = br.readLine()) != null ){
            sb.append(str);
        }
        try {
            JSONObject jObj = new JSONObject(sb.toString());
            return jObj;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

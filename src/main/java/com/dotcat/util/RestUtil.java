package com.dotcat.util;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;

public class RestUtil {
    private static Gson gson = new Gson();

    public static <T> T getFromJson(HttpServletRequest req, Class<T> clazz) {
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gson.fromJson(sb.toString(), clazz);
    }

    public static <T> String toJson(T t) {
        return gson.toJson(t);
    }
}

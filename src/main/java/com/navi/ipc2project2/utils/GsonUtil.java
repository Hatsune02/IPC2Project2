package com.navi.ipc2project2.utils;

import com.google.gson.Gson;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;

public class GsonUtil<T> {
    private final Gson gson;

    public GsonUtil() {
        gson = new Gson();
    }

    public void sendAsJson(HttpServletResponse response, Object object) throws IOException {
        response.setContentType("application/json");

        String res = gson.toJson(object);
        var out = response.getWriter();
        out.print(res);
    }
    public T readFromJson(HttpServletRequest request, Class<T> classT) throws IOException {
        var buffer = new StringBuilder();
        var reader = request.getReader();
        String line;
        while((line = reader.readLine())!=null) buffer.append(line);

        String payload = buffer.toString();
        return gson.fromJson(payload,classT);
    }

}

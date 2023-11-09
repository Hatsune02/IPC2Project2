package com.navi.jobhub.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/file")
public class FileController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var buffer = new StringBuilder();
        var reader = request.getReader();

        String line;
        while ((line = reader.readLine()) != null) buffer.append(line);

        var  content = buffer.toString();

        System.out.println(content);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");

        var out = response.getWriter();

        out.print("{\"message\": \"Contenido archivo guardado\"}");
    }
}

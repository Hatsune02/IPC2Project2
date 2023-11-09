package com.navi.jobhub.controller;

import com.navi.jobhub.model.Category;
import com.navi.jobhub.service.CategoryService;
import com.navi.jobhub.utils.GsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

    @WebServlet("/categories/*")
public class CategoryController extends HttpServlet {
    private final GsonUtil<Category> gsonCategory;
    private final CategoryService categoryService;

    public CategoryController(){
        gsonCategory = new GsonUtil<>();
        categoryService = new CategoryService();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if(pathInfo == null || pathInfo.equals("/")){
            var categories = categoryService.categoryList();

            response.setStatus(HttpServletResponse.SC_OK);
            gsonCategory.sendAsJson(response, categories);
            return;
        }

        int categoryId = processPath(request, response);
        response.setStatus(HttpServletResponse.SC_OK);
        gsonCategory.sendAsJson(response,categoryService.viewCategory(categoryId));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if(pathInfo == null || pathInfo.equals("/")){
            var category = gsonCategory.readFromJson(request, Category.class);
            categoryService.create(category);
            category = categoryService.newCategory();
            response.setStatus(HttpServletResponse.SC_CREATED);
            gsonCategory.sendAsJson(response, category);
        }
        else{
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processPath(request, response);
        var category = gsonCategory.readFromJson(request, Category.class);
        categoryService.update(category);
        gsonCategory.sendAsJson(response, category);
    }

    private int processPath(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String pathInfo = request.getPathInfo();
        String httpMethod = request.getMethod();

        if(httpMethod.equals("PUT") || httpMethod.equals("DELETE")) {
            if(pathInfo == null || pathInfo.equals("/")){

                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return -1;
            }
        }

        String[] splits = pathInfo.split("/");
        if(splits.length != 2) {

            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return -1;
        }

        String id = splits[1];

        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return -1;
        }

        if(categoryService.viewCategory(Integer.parseInt(id)) == null) {

            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return -1;
        }

        return Integer.parseInt(id);
    }
}

package com.navi.jobhub.controller;

import com.navi.jobhub.model.Commission;
import com.navi.jobhub.service.CommissionService;
import com.navi.jobhub.utils.GsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/commissions/*")
public class CommissionController extends HttpServlet {
    private final GsonUtil<Commission> gsonCategory;
    private final CommissionService commissionService;
    public CommissionController(){
        gsonCategory = new GsonUtil<>();
        commissionService = new CommissionService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if(pathInfo == null || pathInfo.equals("/")){
            var commissions = commissionService.listAll();

            response.setStatus(HttpServletResponse.SC_OK);
            gsonCategory.sendAsJson(response, commissions);
            return;
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if(pathInfo == null || pathInfo.equals("/")){
            var commission = gsonCategory.readFromJson(request, Commission.class);
            commissionService.updateCommission(commission.getCommission());
            response.setStatus(HttpServletResponse.SC_CREATED);
            gsonCategory.sendAsJson(response, commission);
            return;
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);

    }
}

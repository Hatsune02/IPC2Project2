package com.navi.jobhub.controller;

import com.navi.jobhub.model.Category;
import com.navi.jobhub.model.JobOffer;
import com.navi.jobhub.service.JobOfferService;
import com.navi.jobhub.utils.GsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/jobOffers/*")
public class JobOfferController extends HttpServlet {
    private final GsonUtil<JobOffer> gsonJob;
    private final JobOfferService jobOfferService;
    public JobOfferController(){
        jobOfferService = new JobOfferService();
        gsonJob = new GsonUtil<JobOffer>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if(pathInfo == null || pathInfo.equals("/")){
            var offer = jobOfferService.listAll();

            response.setStatus(HttpServletResponse.SC_OK);
            gsonJob.sendAsJson(response, offer);
            return;
        }

        int offerId = processPath(request, response);
        response.setStatus(HttpServletResponse.SC_OK);
        gsonJob.sendAsJson(response,jobOfferService.viewJobOffer(offerId));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if(pathInfo == null || pathInfo.equals("/")){
            var offer = gsonJob.readFromJson(request, JobOffer.class);
            System.out.println(offer);
            jobOfferService.createJobOffer(offer);
            response.setStatus(HttpServletResponse.SC_CREATED);
            gsonJob.sendAsJson(response, offer);
        }
        else{
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processPath(request, response);
        var category = gsonJob.readFromJson(request, JobOffer.class);
        jobOfferService.update(category);
        gsonJob.sendAsJson(response, category);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

        if(jobOfferService.viewJobOffer(Integer.parseInt(id)) == null) {

            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return -1;
        }

        return Integer.parseInt(id);
    }
}

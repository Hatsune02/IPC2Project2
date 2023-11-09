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

@WebServlet("/jobOfferE/*")
public class JobOfferEmployerController extends HttpServlet {
    private final GsonUtil<JobOffer> gsonJob;
    private final JobOfferService jobOfferService;
    public JobOfferEmployerController(){
        jobOfferService = new JobOfferService();
        gsonJob = new GsonUtil<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if(pathInfo == null || pathInfo.equals("/")){
            var offers = jobOfferService.listAll();

            response.setStatus(HttpServletResponse.SC_OK);
            gsonJob.sendAsJson(response, offers);
            return;
        }

        String[] splits = pathInfo.split("/");

        if(splits.length > 3) {

            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String id = splits[1];

        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if(jobOfferService.filterEmployer(Integer.parseInt(id)) == null) {

            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        int employerId = Integer.parseInt(id);

        if(splits.length == 2){
            response.setStatus(HttpServletResponse.SC_OK);
            gsonJob.sendAsJson(response,jobOfferService.filterEmployer(employerId));
            return;
        }

        String filter = splits[2];
        if(filter.equals("interview")){

        }
        else if(filter.equals("active"))



        response.setStatus(HttpServletResponse.SC_OK);
        gsonJob.sendAsJson(response,jobOfferService.filterEmployer(employerId));
    }
}

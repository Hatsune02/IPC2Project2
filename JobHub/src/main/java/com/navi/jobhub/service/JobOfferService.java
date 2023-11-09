package com.navi.jobhub.service;

import com.navi.jobhub.data.*;
import com.navi.jobhub.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JobOfferService {
    private final UserDAO userDAO;
    private final JobOfferDAO jobOfferDAO;
    private final InterviewDAO interviewDAO;
    private final RequestDAO requestDAO;
    private final CategoryDAO categoryDAO;

    public JobOfferService(){
        userDAO = new UserDAO();
        jobOfferDAO = new JobOfferDAO();
        interviewDAO = new InterviewDAO();
        requestDAO = new RequestDAO();
        categoryDAO = new CategoryDAO();
    }

    public void createJobOffer(JobOffer jobOffer){
        jobOfferDAO.insert(jobOffer);
    }
    public void createInterview(Interview interview){
        interviewDAO.insert(interview);
    }
    public void update(JobOffer jobOffer){
        jobOfferDAO.update(jobOffer);
    }
    public List<JobOffer> listAll(){
        List<JobOffer> jobOffers = jobOfferDAO.select();
        int id;
        for(JobOffer offer:jobOffers){
            id = offer.getId();
            offer.setEmployer(userDAO.viewUser(offer.getEmployerId()).getName());
            offer.setCategory(categoryDAO.viewCategory(offer.getCategoryId()).getName());
            offer.setRequests(requestDAO.requestsOffer(id));
            offer.setInterviews(interviewDAO.interviewsOffer(id));
        }
        return jobOffers;
    }
    public List<JobOffer> filterEmployer(int employerId){
        List<JobOffer> jobOffers = listAll();
        return jobOffers.stream().filter(jobOffer -> jobOffer.getEmployerId() == employerId).collect(Collectors.toList());
    }

    public JobOffer viewJobOffer(int id){
        return jobOfferDAO.viewJobOffer(id);
    }

    public List<JobOffer> requestsUser(int userId){
        List<JobOffer> jobOffers = new ArrayList<>();
        for(Request request:requestDAO.requestsUser(userId)){
            List<Request> requests = new ArrayList<>();
            requests.add(request);
            JobOffer offer = jobOfferDAO.viewJobOffer(request.getOfferId());
            offer.setRequests(requests);
            jobOffers.add(offer);

        }
        return jobOffers;
    }
}

package com.navi.jobhub.service;

import com.navi.jobhub.data.*;
import com.navi.jobhub.model.*;

import java.util.ArrayList;
import java.util.List;

public class EmployerService {
    private final UserDAO userDAO;
    private final EmployerDAO employerDAO;
    private final CardDAO cardDAO;
    private final PhoneNumberDAO phoneNumberDAO;

    public EmployerService(){
        userDAO = new UserDAO();
        employerDAO = new EmployerDAO();
        cardDAO = new CardDAO();
        phoneNumberDAO = new PhoneNumberDAO();
    }

    public void createUser(User user, ArrayList<PhoneNumber> phoneNumbers){
        userDAO.insert(user);
        for(PhoneNumber phoneNumber: phoneNumbers){
            phoneNumberDAO.insert(phoneNumber);
        }
    }
    public void createEmployer(Employer employer, Card card){
        employerDAO.insert(employer);
        cardDAO.insert(card);
    }

    public List<Employer> listAll(){
        List<Employer> employers = employerDAO.select();
        User user;
        int id;
        for (Employer employer : employers) {
            id = employer.getId();
            user = userDAO.viewUser(id);
            employer.completeInfo(user);
            employer.setPhoneNumbers(phoneNumberDAO.phoneNumbersUser(id));
            employer.setCard(cardDAO.viewCard(id));
        }

        return employers;
    }

    public Employer viewEmployer(int id){
        for(Employer employer:listAll()){
            if(employer.getId() == id) return employer;
        }
        return null;
    }
}

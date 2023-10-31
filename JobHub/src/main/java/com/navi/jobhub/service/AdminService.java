package com.navi.jobhub.service;

import com.navi.jobhub.data.PhoneNumberDAO;
import com.navi.jobhub.data.UserDAO;
import com.navi.jobhub.model.PhoneNumber;
import com.navi.jobhub.model.User;

import java.util.ArrayList;

public class AdminService {
    private final UserDAO userDAO;
    private final PhoneNumberDAO phoneNumberDAO;

    public AdminService(){
        userDAO = new UserDAO();
        phoneNumberDAO = new PhoneNumberDAO();
    }
    public void createUser(User user, ArrayList<PhoneNumber> phoneNumbers){
        userDAO.insert(user);
        for(PhoneNumber phoneNumber: phoneNumbers){
            phoneNumberDAO.insert(phoneNumber);
        }
    }
}

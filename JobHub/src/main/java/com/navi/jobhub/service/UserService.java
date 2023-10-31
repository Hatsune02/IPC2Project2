package com.navi.jobhub.service;

import com.navi.jobhub.data.*;
import com.navi.jobhub.model.PhoneNumber;
import com.navi.jobhub.model.User;

import java.util.ArrayList;

public class UserService {
    private final UserDAO userDAO;
    private final EndUserDAO endUserDAO;
    private final UserCategoryDAO userCategoryDAO;
    private final PhoneNumberDAO phoneNumberDAO;

    public UserService(){
        userDAO = new UserDAO();
        endUserDAO = new EndUserDAO();
        userCategoryDAO = new UserCategoryDAO();
        phoneNumberDAO = new PhoneNumberDAO();
    }

    public void createUser(User user, ArrayList<PhoneNumber> phoneNumbers){
        userDAO.insert(user);
        for(PhoneNumber phoneNumber: phoneNumbers){
            phoneNumberDAO.insert(phoneNumber);
        }
    }



}

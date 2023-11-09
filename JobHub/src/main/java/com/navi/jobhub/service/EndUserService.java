package com.navi.jobhub.service;

import com.navi.jobhub.data.*;
import com.navi.jobhub.model.PhoneNumber;
import com.navi.jobhub.model.User;

import java.util.ArrayList;

public class EndUserService {
    private final UserDAO userDAO;
    private final EndUserDAO endUserDAO;
    private final UserCategoryDAO userCategoryDAO;
    private final PhoneNumberDAO phoneNumberDAO;

    public EndUserService(){
        userDAO = new UserDAO();
        endUserDAO = new EndUserDAO();
        userCategoryDAO = new UserCategoryDAO();
        phoneNumberDAO = new PhoneNumberDAO();
    }

    public boolean validate(User user){
        return userDAO.validate(user);
    }
    public User viewUser(String username){
        return userDAO.viewUser(username);
    }





}

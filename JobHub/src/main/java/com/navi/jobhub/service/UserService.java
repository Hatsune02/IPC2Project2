package com.navi.jobhub.service;

import com.navi.jobhub.data.PhoneNumberDAO;
import com.navi.jobhub.data.UserDAO;
import com.navi.jobhub.model.PhoneNumber;
import com.navi.jobhub.model.User;

import java.util.ArrayList;

public class UserService {
    private final UserDAO userDAO;
    private final PhoneNumberDAO phoneNumberDAO;

    public UserService(){
        userDAO = new UserDAO();
        phoneNumberDAO = new PhoneNumberDAO();
    }
    public void createUser(User user, ArrayList<String> phoneNumbers){
        userDAO.insert(user);
        int id = userDAO.searchId(user.getUsername());
        for(String number: phoneNumbers){
            phoneNumberDAO.insert(PhoneNumber.builder().userId(id).number(number).build());
        }
    }
}

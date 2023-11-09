package com.navi.jobhub.controller;

import com.navi.jobhub.model.User;
import com.navi.jobhub.service.EndUserService;
import com.navi.jobhub.utils.GsonUtil;
import com.navi.jobhub.utils.PasswordEncryption;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private final GsonUtil<User> gsonLogin;
    private final EndUserService userService;

    public LoginController(){
        gsonLogin = new GsonUtil<>();
        userService = new EndUserService();

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var user = gsonLogin.readFromJson(request, User.class);
        user.setPassword(PasswordEncryption.encryptPassword(user.getPassword()));
        if(userService.validate(user)){
            response.setStatus(HttpServletResponse.SC_OK);
            user = userService.viewUser(user.getUsername());
            user.setPassword(null);
            gsonLogin.sendAsJson(response, user);
        }
        else response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

}

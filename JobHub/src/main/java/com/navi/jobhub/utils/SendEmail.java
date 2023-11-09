package com.navi.jobhub.utils;

import javax.mail.Session;
import java.util.Properties;

public class SendEmail {
    Properties properties = new Properties();
    public void sendEmail(String emailR){
        properties.setProperty("email.smtp.host","smtp.gmail.com");
        properties.setProperty("email.smtp.starttls","true");
        properties.setProperty("email.smtp.port","587");
        properties.setProperty("email.smtp.auth","true");

        Session session = Session.getDefaultInstance(properties);

        String email = "tzunundonavin@gmail.com";
        String password = "123:doykami1";

    }
}

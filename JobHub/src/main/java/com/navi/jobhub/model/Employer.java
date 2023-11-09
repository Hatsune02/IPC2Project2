package com.navi.jobhub.model;

import lombok.*;

import java.util.ArrayList;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Employer extends User{
    private String mission;
    private String vision;
    private Card card;

    public void completeInfo(User user){
        setId(user.getId());
        setType(user.getType());
        setName(user.getName());
        setUsername(user.getUsername());
        setPassword(user.getPassword());
        setAddress(user.getAddress());
        setEmail(user.getEmail());
        setCUI(user.getCUI());
        setBirth(user.getBirth());
        setVerify(user.getVerify());
        setPhoneNumbers(user.getPhoneNumbers());
    }
}

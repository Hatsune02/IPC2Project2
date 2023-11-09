package com.navi.jobhub.model;

import lombok.*;

import java.util.ArrayList;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class EndUser extends User{
    private ArrayList<Category> categories;

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

package com.navi.jobhub.model;

import lombok.*;

import java.util.ArrayList;

@Getter @Setter @ToString @Builder
@AllArgsConstructor @NoArgsConstructor
public class EndUser extends User{
    private ArrayList<Category> categories;

    public EndUser(User user){
        super(user.getId(), user.getType(), user.getName(),user.getUsername(),
                user.getPassword(), user.getAddress(),user.getEmail(), user.getCUI(),
                user.getBirth(), user.getVerify(), user.getPhoneNumbers());
    }
}

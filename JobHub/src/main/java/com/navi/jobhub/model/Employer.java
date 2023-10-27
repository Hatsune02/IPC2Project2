package com.navi.jobhub.model;

import lombok.*;

import java.util.ArrayList;

@Getter @Setter @ToString @Builder
@AllArgsConstructor @NoArgsConstructor
public class Employer extends User{
    private String mission;
    private String vision;
    private Card card;

    public Employer(User user, Employer employer){
        super(user.getId(), user.getType(), user.getName(),user.getUsername(),
                user.getPassword(), user.getAddress(),user.getEmail(), user.getCUI(),
                user.getBirth(), user.getVerify(), user.getPhoneNumbers());
        this.mission = employer.getMission();
        this.vision = employer.getVision();
    }
}

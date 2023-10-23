package com.navi.jobhub.model;

import lombok.*;

@Getter @Setter @ToString
public class Employer extends User{
    private String mission;
    private String vision;

    public Employer() {
    }

    public Employer(int id) {
        super(id);
    }

    public Employer(int id, String mission, String vision) {
        super(id);
        this.mission = mission;
        this.vision = vision;
    }
}

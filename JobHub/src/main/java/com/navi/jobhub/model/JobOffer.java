package com.navi.jobhub.model;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter @Setter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
public class JobOffer {
    private int id;
    private String name;
    private String description;
    private int employerId;
    private int categoryId;
    private String state;
    private Date created;
    private Date end;
    private double salary;
    private String modality;
    private String location;
    private String details;
    private int chosenUser;
    private List<Request> requests;
    private List<Interview> interviews;

}

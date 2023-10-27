package com.navi.jobhub.model;

import lombok.*;

import java.sql.Date;
import java.sql.Time;

@Getter @Setter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
public class Interview {
    private int id;
    private int offerId;
    private int userId;
    private Date date;
    private Time time;
    private String location;
    private String state;
    private String notes;
}

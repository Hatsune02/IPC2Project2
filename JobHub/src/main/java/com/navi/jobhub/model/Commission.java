package com.navi.jobhub.model;

import lombok.*;

import java.sql.Date;

@Getter @Setter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class Commission {
    private int id;
    private Date startDate;
    private Date endDate;
    private double commission;
}

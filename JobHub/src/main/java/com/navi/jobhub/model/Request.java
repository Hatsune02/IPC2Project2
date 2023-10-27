package com.navi.jobhub.model;

import lombok.*;

@Getter @Setter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
public class Request {
    private int id;
    private int offerId;
    private int userId;
    private String message;
    private String state;
}

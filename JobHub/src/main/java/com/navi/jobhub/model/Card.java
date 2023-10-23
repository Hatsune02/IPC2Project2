package com.navi.jobhub.model;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Card {
    private int userId;
    private String owner;
    private String number;
    private String password;

}

package com.navi.jobhub.model;

import lombok.*;

@Getter @Setter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
public class PhoneNumber {
    private int userId;
    private String number;

}

package com.navi.jobhub.model;

import lombok.*;

@Getter @Setter @ToString
public class PhoneNumbers {
    private int userId;
    private String number;

    public PhoneNumbers() {
    }

    public PhoneNumbers(int userId, String number) {
        this.userId = userId;
        this.number = number;
    }
}

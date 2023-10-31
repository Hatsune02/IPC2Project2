package com.navi.jobhub.model;

import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
public class User {
    private int id;
    private int type;
    private String name;
    private String username;
    private String password;
    private String address;
    private String email;
    private String CUI;
    private Date birth;
    private int verify;
    private List<PhoneNumber> phoneNumbers;

}

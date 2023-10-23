package com.navi.jobhub.model;

import lombok.*;

import java.sql.Date;

@Getter @Setter @ToString
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
    private boolean verify;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, int type, String name, String username, String password, String address, String email, String CUI, Date birth, boolean verify) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
        this.CUI = CUI;
        this.birth = birth;
        this.verify = verify;
    }
}

package com.navi.jobhub.model;

import lombok.*;

@Getter @Setter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
public class Category {
    private int id;
    private String name;
    private String description;

}

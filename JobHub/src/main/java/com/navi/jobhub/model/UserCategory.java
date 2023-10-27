package com.navi.jobhub.model;

import lombok.*;

@Getter @Setter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
public class UserCategory {
    private int userId;
    private int categoryId;

}

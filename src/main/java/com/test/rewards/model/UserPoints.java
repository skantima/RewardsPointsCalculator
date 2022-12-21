package com.test.rewards.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPoints {

    private String userId;
    private Points points;
    private Points totalPoints;
}

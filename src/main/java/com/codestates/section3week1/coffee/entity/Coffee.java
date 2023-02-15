package com.codestates.section3week1.coffee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {
    private long coffeeId;
    private String korName;
    private String engName;
    private int price;

}

package com.codestates.section3week1.coffee;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CoffeePostDto {
    @NotBlank
    private String korName;
    @NotBlank
    @Pattern(regexp = "^([a-zA-Z]+\\s?[a-zA-Z])+$") // 영어만 허용
    // [A]* : A가 0개 이상이다.
    // [A]+ : A가 1개 이상이다.
    // \\s? : 공백이 0개 or 1개다.
    // 즉, "^([a-zA-Z]+\\s?[a-zA-Z])*$"는 알파벳(몇개인지 상관x) + 공백 + 알파벳(몇개인지 상관x)이다.
    // n\d* : n 뒤에 숫자가 0개 이상이라는 의미. “n”, “n1”, “n123” 에 모두 매치된다.
    private String engName;
    @Range(min = 100, max= 50000)
    private Integer price;

    public String getKorName() {
        return korName;
    }

    public void setKorName(String korName) {
        this.korName = korName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

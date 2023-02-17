package com.codestates.section3week1.coffee.dto;

import com.codestates.section3week1.validator.NotSpace;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.Pattern;

public class CoffeePatchDto {
    private long coffeeId;
    @NotSpace // null가능하게 해줘야 함
    private String korName;
    @Pattern(regexp = "^([a-zA-Z]+\\s?[a-zA-Z])*$")
    private String engName;
    @Range(min = 100, max= 50000)
    private Integer price; // int로 하면 null이 안들어간다. 기본값인 0이 들어가기 때문에 range에 걸려서 선택적으로 사용 못함

    public long getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(long coffeeId) {
        this.coffeeId = coffeeId;
    }

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}

package com.codestates.section3week1.coffee.mapstruct.mapper;

import com.codestates.section3week1.coffee.CoffeePatchDto;
import com.codestates.section3week1.coffee.CoffeePostDto;
import com.codestates.section3week1.coffee.CoffeeResponseDto;
import com.codestates.section3week1.coffee.entity.Coffee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {

    Coffee coffeePostDtoToCoffee(CoffeePostDto coffeePostDto);
    Coffee coffeePatchDtoToCoffee(CoffeePatchDto coffeePatchDto);
    CoffeeResponseDto coffeeToCoffeeResponseDto(Coffee coffee);

}
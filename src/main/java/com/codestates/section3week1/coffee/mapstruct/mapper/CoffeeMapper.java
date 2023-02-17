package com.codestates.section3week1.coffee.mapstruct.mapper;

import com.codestates.section3week1.coffee.dto.CoffeePatchDto;
import com.codestates.section3week1.coffee.dto.CoffeePostDto;
import com.codestates.section3week1.coffee.dto.CoffeeResponseDto;
import com.codestates.section3week1.coffee.entity.Coffee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {

    Coffee coffeePostDtoToCoffee(CoffeePostDto coffeePostDto);
    Coffee coffeePatchDtoToCoffee(CoffeePatchDto coffeePatchDto);
    CoffeeResponseDto coffeeToCoffeeResponseDto(Coffee coffee);

}
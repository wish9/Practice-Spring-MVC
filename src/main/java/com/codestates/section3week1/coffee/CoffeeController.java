package com.codestates.section3week1.coffee;

import com.codestates.section3week1.coffee.entity.Coffee;
import com.codestates.section3week1.coffee.mapstruct.mapper.CoffeeMapper;
import com.codestates.section3week1.coffee.service.CoffeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v5/coffees")
@Validated
public class CoffeeController {

    private final CoffeeService coffeeService;
    private final CoffeeMapper mapper;

    public CoffeeController(CoffeeService coffeeService, CoffeeMapper mapper) {
        this.coffeeService = coffeeService;
        this.mapper = mapper;
    }


    @PostMapping
    public ResponseEntity postCoffee(@Valid @RequestBody CoffeePostDto coffeePostDto) {


        Coffee coffee = mapper.coffeePostDtoToCoffee(coffeePostDto);

        Coffee response = coffeeService.createCoffee(coffee);

        return new ResponseEntity<>(mapper.coffeeToCoffeeResponseDto(response),
                HttpStatus.CREATED);
    }

    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee(@PathVariable("coffee-id") @Positive long coffeeId,
                                      @Valid @RequestBody CoffeePatchDto coffeePatchDto) {
        coffeePatchDto.setCoffeeId(coffeeId);

        Coffee coffee = mapper.coffeePatchDtoToCoffee(coffeePatchDto);
        Coffee response = coffeeService.updateCoffee(coffee);

        return new ResponseEntity<>(mapper.coffeeToCoffeeResponseDto(response),
                HttpStatus.OK);
    }

    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@PathVariable("coffee-id") long coffeeId) {
        Coffee response = coffeeService.findCoffee(coffeeId);
        return new ResponseEntity<>(mapper.coffeeToCoffeeResponseDto(response),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCoffees() {
        List<Coffee> coffees = coffeeService.findCoffees();
        List<CoffeeResponseDto> response =
                coffees.stream()
                        .map(coffee -> mapper.coffeeToCoffeeResponseDto(coffee))
                        .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") long coffeeId) {
        System.out.println("# delete coffee");
        coffeeService.deleteCoffee(coffeeId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

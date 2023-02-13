package com.codestates.coffee;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/v1/members", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CoffeeController {

    @PostMapping
    public ResponseEntity postCoffee(@RequestParam("engName") String engName, // 회원 정보를 등록해주는 핸들러 메서드
                                     @RequestParam("korName") String korName,
                                     @RequestParam("price") int price) {
        Map<String, Object> map = new HashMap<>();
        map.put("engName", engName);
        map.put("korName", korName);
        map.put("price", price);

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @GetMapping("/{coffee-id}") // 클라이언트가 서버에 리소스를 조회할 때 사용하는 애너테이션
    public  ResponseEntity getCoffee(@PathVariable("coffee-id")long coffeeId){ // 특정 회원의 정보를 클라이언트 쪽에 제공하는 핸들러 메서드
        System.out.println("# coffeeId: " + coffeeId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers() { // 회원 목록을 클라이언트에게 제공하는 핸들러 메서드
        System.out.println("# get coffees");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

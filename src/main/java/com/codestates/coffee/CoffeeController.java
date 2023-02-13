package com.codestates.coffee;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/members", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CoffeeController {

    @PostMapping
    public String postCoffee(@RequestParam("engName") String engName, // 회원 정보를 등록해주는 핸들러 메서드
                             @RequestParam("korname") String korName,
                             @RequestParam("price") int price) {
        System.out.println("# engName: " + engName);
        System.out.println("# korName: " + korName);
        System.out.println("# price: " + price);

        String response =
                "{\"" +
                        "engName\":\"" + engName + "\"," +
                        "\"korName\":\"" + korName + "\",\"" +
                        "price\":\"" + price +
                        "\"}"; //  JSON 형식에 맞추어 문자열을 작성
        return response;
    }
}

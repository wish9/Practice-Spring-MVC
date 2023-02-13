package com.codestates.order;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
    @PostMapping
    //회원 고객이 주문한 커피 주문 정보를 등록해주는 핸들러 메서드
    public String postOrder(@RequestParam("memberId") long memberId,
                            @RequestParam("coffeeId") long coffeeId) {
        System.out.println("# memberId: " + memberId);
        System.out.println("# coffeeId: " + coffeeId);

        String response =
                "{\"" +
                        "memberId\":\""+memberId+"\"," +
                        "\"coffeeId\":\""+coffeeId+"\"" +
                        "}";
        return response;
    }

    @GetMapping("/{order-id}")
    public String getOrder(@PathVariable("order-id") long orderId) {
        System.out.println("# orderId: " + orderId);

        // not implementation
        return null;
    }

    @GetMapping
    public String getOrders() {
        System.out.println("# get Orders");

        // not implementation
        return null;
    }
}

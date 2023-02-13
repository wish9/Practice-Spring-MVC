package com.codestates.member;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/members", produces = {MediaType.APPLICATION_JSON_VALUE})
//클라이언트의 요청과 클라이언트 요청을 처리하는 핸들러 메서드(Handler Method)를 매핑해준다.
public class MemberController { // 회원 관리를 위한 클래스
    @PostMapping // 클라이언트의 요청 데이터(request body)를 서버에 생성할 때 사용하는 애너테이션
    public String postMember(@RequestParam("email") String email, // 회원 정보를 등록해주는 핸들러 메서드
                             @RequestParam("name") String name,
                             @RequestParam("phone") String phone) {
        System.out.println("# email: " + email);
        System.out.println("# name: " + name);
        System.out.println("# phone: " + phone);

        String response =
                "{\"" +
                        "email\":\""+email+"\"," +
                        "\"name\":\""+name+"\",\"" +
                        "phone\":\"" + phone+
                        "\"}"; //  JSON 형식에 맞추어 문자열을 작성
        return response;
    }

    @GetMapping("/{member-id}") // 클라이언트가 서버에 리소스를 조회할 때 사용하는 애너테이션
    public  String getMember(@PathVariable("member-id")long memberId){ // 특정 회원의 정보를 클라이언트 쪽에 제공하는 핸들러 메서드
        System.out.println("# memberId: " + memberId);

        return null;
    }

    @GetMapping
    public String getMembers() { // 회원 목록을 클라이언트에게 제공하는 핸들러 메서드
        System.out.println("# get Members");

        return null;
    }
}

package com.codestates.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/members")
//클라이언트의 요청과 클라이언트 요청을 처리하는 핸들러 메서드(Handler Method)를 매핑해준다.
public class MemberController { // 회원 관리를 위한 클래스
    @PostMapping // 클라이언트의 요청 데이터(request body)를 서버에 생성할 때 사용하는 애너테이션
    public ResponseEntity postMember(@RequestParam("email") String email, // 회원 정보를 등록해주는 핸들러 메서드
                             @RequestParam("name") String name,
                             @RequestParam("phone") String phone) {
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("name", name);
        map.put("phone", phone);

        return new ResponseEntity<>(map, HttpStatus.CREATED); // Map 객체를 리턴하게 되면  JSON 형식으로 자동 변환
        // ResponseEntity 객체로 데이터를 래핑함으로써 HTTP 응답 상태를 함께 전달
    }

    @GetMapping("/{member-id}") // 클라이언트가 서버에 리소스를 조회할 때 사용하는 애너테이션
    public  ResponseEntity getMember(@PathVariable("member-id")long memberId){ // 특정 회원의 정보를 클라이언트 쪽에 제공하는 핸들러 메서드
        System.out.println("# memberId: " + memberId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers() { // 회원 목록을 클라이언트에게 제공하는 핸들러 메서드
        System.out.println("# get Members");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

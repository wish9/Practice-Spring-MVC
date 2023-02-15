package com.codestates.section3week1.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

// 현재 상태로는 2가지 문제점이 있다.
// MemberController의 핸들러 메서드가 DTO 클래스를 엔티티(Entity) 클래스로 변환하는 작업까지 도맡아서 하고 있다.
// 엔티티(Entity) 클래스의 객체를 클라이언트의 응답으로 전송함으로써 계층 간의 역할 분리가 이루어지지 않았다.

@RestController // Spring Bean 자동등록 기능 포함
@RequestMapping("/v1/members")
@Validated // @PathVariable이 추가된 변수에 유효성 검증하려면 붙여야함
//클라이언트의 요청과 클라이언트 요청을 처리하는 핸들러 메서드(Handler Method)를 매핑해준다.
public class MemberController { // 회원 관리를 위한 클래스
    private final MemberService memberService;

    public MemberController(MemberService memberService) { // Spring이 애플리케이션 로드시 자동으로 주입해줌
        this.memberService = memberService;
    }


    @PostMapping // 클라이언트의 요청 데이터(request body)를 서버에 생성할 때 사용하는 애너테이션
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberPostDto){

        Member member = new Member();
        member.setEmail(memberPostDto.getEmail());
        member.setName(memberPostDto.getName());
        member.setPhone(memberPostDto.getPhone());

        Member response = memberService.createMember(member);

        return new ResponseEntity<>(response, HttpStatus.CREATED); // Map 객체를 리턴하게 되면  JSON 형식으로 자동 변환
        // ResponseEntity 객체로 데이터를 래핑함으로써 HTTP 응답 상태를 함께 전달
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Positive long memberId, // @Min(1) =  1 이상의 숫자일 경우에만 유효성 검증 통과
                                      @Valid @RequestBody MemberPatchDto memberPatchDto) {
        memberPatchDto.setMemberId(memberId);

        Member member = new Member();
        member.setMemberId(memberPatchDto.getMemberId());
        member.setName(memberPatchDto.getName());
        member.setPhone(memberPatchDto.getPhone());

        Member response = memberService.updateMember(member);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{member-id}") // 클라이언트가 서버에 리소스를 조회할 때 사용하는 애너테이션
    public  ResponseEntity getMember(@PathVariable("member-id") @Positive long memberId){ // 특정 회원의 정보를 클라이언트 쪽에 제공하는 핸들러 메서드
        Member response = memberService.findMember(memberId);
        System.out.println("# get Member");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers() { // 모든회원 정보 목록을 클라이언트에게 제공하는 핸들러 메서드
        List<Member> response = memberService.findMembers();
        System.out.println("# get Members List");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive long memberId) {
        memberService.deleteMember(memberId);
        System.out.println("# delete member");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

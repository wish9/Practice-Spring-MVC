package com.codestates.section3week1.member;

import com.codestates.section3week1.member.dto.MemberPatchDto;
import com.codestates.section3week1.member.dto.MemberPostDto;
import com.codestates.section3week1.member.dto.MemberResponseDto;
import com.codestates.section3week1.member.entity.Member;
import com.codestates.section3week1.member.mapstruct.mapper.MemberMapper;
import com.codestates.section3week1.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

// 이전버전 상태로는 2가지 문제점이 있다.
// MemberController의 핸들러 메서드가 DTO 클래스를 엔티티(Entity) 클래스로 변환하는 작업까지 도맡아서 하고 있다.
// DTO 클래스-> MemberController -> 엔티티(Entity)
// 엔티티(Entity) 클래스의 객체를 클라이언트의 응답으로 전송함으로써 계층 간의 역할 분리가 이루어지지 않았다.
// 엔티티(Entity) -> 응답

// 현재 상태는 Mapper 클래스를 이용해 위 문제점을 해결했다.
// DTO 클래스-> MemberMapper -> 엔티티(Entity)
// 엔티티(Entity) -> MemberMapper -> DTO 클래스 -> 응답

@RestController // Spring Bean 자동등록 기능 포함
@RequestMapping("/v1/members")
@Validated // @PathVariable이 추가된 변수에 유효성 검증하려면 붙여야함
//클라이언트의 요청과 클라이언트 요청을 처리하는 핸들러 메서드(Handler Method)를 매핑해준다.
public class MemberController { // 회원 관리를 위한 클래스
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    public MemberController(MemberService memberService, MemberMapper memberMapper) { // 느슨한결합 // Spring이 애플리케이션 로드시 자동으로 주입해줌
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }


    @PostMapping // 클라이언트의 요청 데이터(request body)를 서버에 생성할 때 사용하는 애너테이션
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberPostDto){

        Member member = memberMapper.memberPostDtoToMember(memberPostDto);
        Member response = memberService.createMember(member);

        return new ResponseEntity<>(memberMapper.memberToMemberResponseDto(response),
                HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Positive long memberId,
                                      @Valid @RequestBody MemberPatchDto memberPatchDto) {
        memberPatchDto.setMemberId(memberId);

        Member member = memberMapper.memberPatchDtoToMember(memberPatchDto);
        Member response = memberService.updateMember(member);

        return new ResponseEntity<>(memberMapper.memberToMemberResponseDto(response), HttpStatus.OK);
    }

    @GetMapping("/{member-id}") // 클라이언트가 서버에 리소스를 조회할 때 사용하는 애너테이션
    public  ResponseEntity getMember(@PathVariable("member-id") @Positive long memberId){ // 특정 회원의 정보를 클라이언트 쪽에 제공하는 핸들러 메서드
        Member response = memberService.findMember(memberId);
        System.out.println("# get Member");
        return new ResponseEntity<>(memberMapper.memberToMemberResponseDto(response),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers() { // 모든회원 정보 목록을 클라이언트에게 제공하는 핸들러 메서드
        List<Member> members = memberService.findMembers();
        List<MemberResponseDto> response = members.stream()
                    .map(member -> memberMapper.memberToMemberResponseDto(member))
                    .collect(Collectors.toList());

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

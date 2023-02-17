package com.codestates.section3week1.member.mapper;

import com.codestates.section3week1.member.entity.Member;
import com.codestates.section3week1.member.dto.MemberPatchDto;
import com.codestates.section3week1.member.dto.MemberPostDto;
import com.codestates.section3week1.member.dto.MemberResponseDto;
import org.springframework.stereotype.Component;

@Component // 빈 등록
public class MemberMapper {
    public Member memberPostDtoToMember(MemberPostDto memberPostDto) { // MemberPostDto를 Member로 변환
        return new Member(0L,
                memberPostDto.getEmail(),
                memberPostDto.getName(),
                memberPostDto.getPhone());
    }

    public Member memberPatchDtoToMember(MemberPatchDto memberPatchDto) { // MemberPatchDto를 Member로 변환
        return new Member(memberPatchDto.getMemberId(),
                null, // MemberPatchDto객체에는 email 없으니까 null (이메일 수정 안되게 해뒀음)
                memberPatchDto.getName(),
                memberPatchDto.getPhone());
    }

    public MemberResponseDto memberToMemberResponseDto(Member member) { // Member를 MemberResponseDto로 변환
        return new MemberResponseDto(member.getMemberId(),
                member.getEmail(),
                member.getName(),
                member.getPhone());
    }
}

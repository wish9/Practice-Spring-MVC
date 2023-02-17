package com.codestates.section3week1.member.service;

import com.codestates.section3week1.exception.BusinessLogicException;
import com.codestates.section3week1.exception.ExceptionCode;
import com.codestates.section3week1.member.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Spring Bean 등록
public class MemberService {
    public Member createMember(Member member){ // 아직 DB연결 안했으니 대충 쓴거
        Member createMember = member;
        return createMember;
    }

    public Member updateMember(Member member) { // 아직 DB연결 안했으니 대충 쓴거
        Member updatedMember = member;
        return updatedMember;
    }

    public Member findMember(long memberId) { // 아직 DB연결 안했으니 대충 쓴거
        Member member = new Member(memberId, "hgd@gmail.com", "홍길동", "010-1234-5678");
        if(memberId==2) throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND); // 예외처리 확인용으로 추가
        return member;
    }

    public List<Member> findMembers() { // 아직 DB연결 안했으니 대충 쓴거
        List<Member> members = List.of(
                new Member(1, "hgd@gmail.com", "홍길동", "010-1234-5678"),
                new Member(2, "lml@gmail.com", "이몽룡", "010-1111-2222")
        );
        return members;
    }

    public void deleteMember(long memberId) {
        String str = null;
        System.out.println(str.toUpperCase()); // 일부러 NullpointerException 발생시키기
    }
}

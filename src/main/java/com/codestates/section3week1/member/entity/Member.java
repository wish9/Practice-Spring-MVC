package com.codestates.section3week1.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // getter메서드 대신 만들어 줌
@Setter // setter메서드 대신 만들어 줌
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 자동으로 생성
@AllArgsConstructor // (모든 멤버 변수를 파라미터로 갖는) 생성자를 자동으로 생성
public class Member {
    private long memberId;
    private String email;
    private String name;
    private String phone;
}

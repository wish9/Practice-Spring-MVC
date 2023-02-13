package com.codestates.section3week1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // @Component, @Configuration가 붙은 클래스를 자동으로 찾고, Spring Bean으로 등록
public class Section3Week1Application { //엔트리포인트 클래스

	public static void main(String[] args) {
		SpringApplication.run(Section3Week1Application.class, args); // Spring 애플리케이션을 부트스트랩하고, 실행하는 역할
		// 부트스트랩(Bootstrap) : 애플리케이션이 실행되기 전에 여러가지 설정 작업을 수행하여 실행 가능한 애플리케이션으로 만드는 단계를 의미
	}

}

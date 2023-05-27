[블로그 포스팅 주소1 (API 계층1)](https://velog.io/@wish17/%EC%BD%94%EB%93%9C%EC%8A%A4%ED%85%8C%EC%9D%B4%EC%B8%A0-%EB%B0%B1%EC%97%94%EB%93%9C-%EB%B6%80%ED%8A%B8%EC%BA%A0%ED%94%84-41%EC%9D%BC%EC%B0%A8-Section-3)
<br>
[블로그 포스팅 주소2 (API 계층2)](https://velog.io/@wish17/%EC%BD%94%EB%93%9C%EC%8A%A4%ED%85%8C%EC%9D%B4%EC%B8%A0-%EB%B0%B1%EC%97%94%EB%93%9C-%EB%B6%80%ED%8A%B8%EC%BA%A0%ED%94%84-42%EC%9D%BC%EC%B0%A8-Spring-MVC-API-%EA%B3%84%EC%B8%B52)
<br>
[블로그 포스팅 주소3 (서비스 계층](https://velog.io/@wish17/%EC%BD%94%EB%93%9C%EC%8A%A4%ED%85%8C%EC%9D%B4%EC%B8%A0-%EB%B0%B1%EC%97%94%EB%93%9C-%EB%B6%80%ED%8A%B8%EC%BA%A0%ED%94%84-43%EC%9D%BC%EC%B0%A8-Spring-MVC-%EC%84%9C%EB%B9%84%EC%8A%A4-%EA%B3%84%EC%B8%B5)
<br>
[블로그 포스팅 주소4 (예외처리1)](https://velog.io/@wish17/%EC%BD%94%EB%93%9C%EC%8A%A4%ED%85%8C%EC%9D%B4%EC%B8%A0-%EB%B0%B1%EC%97%94%EB%93%9C-%EB%B6%80%ED%8A%B8%EC%BA%A0%ED%94%84-44%EC%9D%BC%EC%B0%A8-Spring-MVC)
<br>
[블로그 포스팅 주소4 (예외처리2)](https://velog.io/@wish17/%EC%BD%94%EB%93%9C%EC%8A%A4%ED%85%8C%EC%9D%B4%EC%B8%A0-%EB%B0%B1%EC%97%94%EB%93%9C-%EB%B6%80%ED%8A%B8%EC%BA%A0%ED%94%84-45%EC%9D%BC%EC%B0%A8-Spring-MVC-%EC%98%88%EC%99%B8-%EC%B2%98%EB%A6%AC2)

# [Spring MVC] API 계층

# Spring MVC 아키텍처

> Spring MVC = ``spring-webmvc``
- 서블릿(Servlet) API를 기반으로 클라이언트의 요청을 처리하는 모듈
- 웹 프레임워크의 한 종류 (Spring MVC 프레임워크라고도 부름)
- 클라이언트의 요청을 편리하게 처리해주는 프레임워크다.

> 서블릿(Servlet)
- 웹 서버의 성능을 향상하기 위해 사용되는 자바 클래스의 일종
- 클라이언트의 요청을 처리하도록 특정 규약에 맞추어서 Java 코드로 작성하는 클래스 파일

cf. ``아파치 톰캣(Apache Tomcat)``은 서블릿들이 웹 애플리케이션으로 실행이 되도록 해주는 서블릿 컨테이너(Servlet Container) 중 하나

### Model

Spring **M**VC 중 M

> Model
- 클라이언트에게 응답으로 돌려주는 작업의 처리 결과 데이터


### View

Spring M**V**C 중 V

> View
- Model 데이터를 이용해서 웹브라우저 같은 클라이언트 애플리케이션의 화면에 보여지는 리소스(Resource)를 제공하는 역할

#### 다양한 View의 형태

- HTML 페이지의 출력
    - 클라이언트 애플리케이션에 보여지는 HTML 페이지를 직접 렌더링해서 클라이언트 측에 전송하는 방식
    - 즉, 기본적인 HTML 태그로 구성된 페이지에 Model 데이터를 채워 넣은 후, 최종적인 HTML 페이지를 만들어서 클라이언트 측에 전송
    - Spring MVC에서 지원하는 HTML 페이지 출력 기술에는 Thymeleaf, FreeMarker, JSP + JSTL, Tiles 등이 있다.

- PDF, Excel 등의 문서 형태로 출력
    - Model 데이터를 가공해서 PDF 문서나 Excel 문서를 만들어서 클라이언트 측에 전송하는 방식
    - 문서 내에서 데이터가 동적으로 변경되어야 하는 경우에 주로 사용

- XML, [JSON](https://ko.wikipedia.org/wiki/JSON) 등 특정 형식의 포맷으로의 변환
    - Model 데이터를 특정 프로토콜 형태로 변환해서 변환된 데이터를 클라이언트 측에 전송하는 방식

#### Java의 객체를 JSON 포맷으로 변환

```java
public class Coffee {
    private String korName;
    private String engName;
    private int price;

    public Coffee(String korName, String engName, int price) {
        this.korName = korName;
        this.engName = engName;
        this.price = price;
    }
}
```

Gson이라는 라이브러리를 사용해서 Coffee 클래스의 객체를 JSON 포맷 형태로 출력

```java
    public class JsonExample {
        public static void main(String[] args) {
            Coffee coffee = new Coffee("아메리카노", "Americano", 3500);
            Gson gson = new Gson();
            String jsonString = gson.toJson(coffee);
    
            System.out.println(jsonString);
        }
    }
    
//출력
{"korName":"아메리카노","engName":"Americano","price":3500}
```

 JSON 포맷은 기본적으로``{”속성”:”값”}`` 형태다.

위와 반대의 과정을 아래 툴로 연습해보며 JSON 형태에 익숙해질 수 있었다.

[JSON 포맷의 문자열을 Java 클래스로 변경해주는 온라인 툴](https://jsonformatter.org/json-to-java)

![](https://velog.velcdn.com/images/wish17/post/f6262c9f-3c87-4e3a-a6b1-e96e3fe45097/image.png)



### Controller

Spring MVC 중 C

> Controller
- 클라이언트 측의 요청을 직접적으로 전달 받는 엔드포인트(Endpoint)로써 Model과 View의 중간에서 상호 작용을 해주는 역할
- 클라이언트 측의 요청을 전달 받아서 비즈니스 로직을 거친 후에 Model 데이터가 만들어지면, 이 Model 데이터를 View로 전달하는 역할

#### Model, View, Controller 간의 처리 흐름

Client가 요청 데이터 전송 
→ Controller가 요청 데이터 수신 
→ 비즈니스 로직 처리 
→ Model 데이터 생성
→ Controller에게 Model 데이터 전달 
→ Controller가 View에게 Model 데이터 전달
→ View가 응답 데이터 생성

***

## Spring MVC의 동작 방식과 구성 요소

[![](https://velog.velcdn.com/images/wish17/post/fa87552a-0f57-40b2-aa6a-ae386e9615dd/image.png)](https://velog.io/@inssg/Spring-MVC-API-%EA%B3%84%EC%B8%B5)


#### Spring MVC의 요청 처리 흐름

- 클라이언트의 요청을 제일 먼저 전달 받는 구성요소는 DispatcherServlet이다.
- DispatcherServlet은 HandlerMapping 인터페이스에게 Controller의 검색을 위임한다.
- DispatcherServlet은 검색된 Controller 정보를 토대로 HandlerAdapter 인터페이스에게 Controller 클래스내에 있는 Handler 메서드의 호출을 위임한다.
- HandlerAdapter 인터페이스는 Controller 클래스의 Handler 메서드를 호출한다.
- DispatcherServlet은 ViewResolver에게 View의 검색을 위임한다.
- DispatcherServlet은 View에게 Model 데이터를 포함한 응답 데이터 생성을 위임한다.
- DispatcherServlet은 최종 응답 데이터를 클라이언트에게 전달한다.

cf.
DispatcherServlet처럼 애플리케이션의 가장 앞단에 배치되어 다른 구성요소들과 상호작용하면서 클라이언트의 요청을 처리하는 패턴을 **Front Controller Pattern**이라고 한다.

***

# Controller

##  Controller 클래스 설계 및 구조 생성

### 패키지 구조 생성

주로 사용하는 패키지 구조 두가지

#### 기능 기반 패키지 구조(package-by-feature)

![](https://velog.velcdn.com/images/wish17/post/892fbb61-2f1f-4b11-b27b-f2186b53776f/image.png)




#### 계층 기반 패키지 구조(package-by-layer)

![](https://velog.velcdn.com/images/wish17/post/42afed0c-0713-43b8-a95f-34ff315a239b/image.png)



테스트와 리팩토링이 용이하고, 향후에 마이크로 서비스 시스템으로의 분리가 상대적으로 용이한 기능 기반 패키지 구조 사용을 권장하기 때문에 학습과정에서는 기능 기반 패키지 구조를 따를 예정이다.

***

### Controller 설계


- Spring Boot 애플리케이션으로서 동작하기 위한 엔트리포인트에는 ``@SpringBootApplication``을 추가한다.
- main() 메서드 내에서 ``SpringApplication.run()``을 호출하면 Spring Boot 기반의 애플리케이션으로 동작한다.
- ``@RestController``를 클래스에 추가함으로써 해당 클래스를 REST API의 리소스(자원, Resource)를 처리하기 위한 API 엔드포인트로 동작하게 해준다.
- ``@RequestMapping``을 Controller 클래스 레벨에 추가하여 클래스 전체에 사용되는 공통 URL(Base URL)을 설정할 수 있다.


***

### 사용한 기능 정리


``@SpringBootApplication``
- 코드 상에서는 보이지 않지만 내부적으로 세가지 일 함
    - 자동 구성을 활성화
    - 애플리케이션 패키지 내에서 ``@Component``가 붙은 클래스를 검색한 후(scan), Spring Bean으로 등록하는 기능을 활성화
    - ``@Configuration`` 이 붙은 클래스를 자동으로 찾아주고, 추가적으로 Spring Bean을 등록하는 기능을 활성화
	

``SpringApplication.run(~~Application.class, args);``

- Spring 애플리케이션을 부트스트랩하고, 실행하는 역할
    - 부트스트랩(Bootstrap) : 애플리케이션이 실행되기 전에 여러가지 설정 작업을 수행하여 실행 가능한 애플리케이션으로 만드는 단계를 의미

``@RestController``

- 클래스가 REST API의 리소스(자원, Resource)를 처리하기 위한 API 엔드포인트로 동작함을 정의한다.

- 애플리케이션 로딩 시, Spring Bean으로 등록해준다.

``@RequestMapping``

- 클라이언트의 요청과 클라이언트 요청을 처리하는 핸들러 메서드(Handler Method)를 매핑해준다.

***

## 핸들러 메서드(Handler Method)

### 기능 정리


``produces``애트리뷰트(Attribute)
- 응답 데이터를 어떤 미디어 타입으로 클라이언트에게 전송할 지를 설정

```java
@RequestMapping(value = "/v1/members", produces = {MediaType.APPLICATION_JSON_VALUE})
```
- 위 예시의 ``MediaType.APPLICATION_JSON_VALUE`` 부분은 JSON 형식의 데이터를 응답 데이터로 전송하겠다는 의미다.

- 위와 같이 produces값을 지정해주지 않으면 **문자열 자체를 전송**한다.


``@PostMapping``

- 클라이언트의 요청 데이터(request body)를 서버에 생성할 때 사용하는 애너테이션

``@RequestParam``

- 핸들러 메서드의 파라미터 종류 중 하나

***

map 메서드 ``put()``, ``replace()`` 차이


 ```java
 Map<String, Integer> map = new HashMap<>();
    map.put("key1", 1);
    map.put("key2", 2);

    map.put("key1", 3); // key1의 value를 3으로 업데이트
    map.put("key3", 4); // key3의 value를 4로 추가

    map.replace("key2", 5); // key2의 value를 5로 업데이트
    map.replace("key4", 6); // key4가 존재하지 않기 때문에, 어떠한 동작도 수행하지 않음

```
put은 key가 해당하는 엔트리가 없어도 새로 만들어서라도 추가해줌
replace는 존재하지 않으면 아무것도 안함


***

``ResponseEntity``사용법

[http 상태코드](https://developer.mozilla.org/ko/docs/Web/HTTP/Status)를 이용하면 됨.

```java
// members + 200 OK
return new ResponseEntity(members, HttpStatus.OK);

// 204 No Content
return new ResponseEntity(HttpStatus.NO_CONTENT);
```

***

### 실습결과

```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/members")
public class MemberController {
    private final Map<Long, Map<String, Object>> members = new HashMap<>();

    @PostConstruct
    public void init() {
        Map<String, Object> member1 = new HashMap<>();
        long memberId = 1L;
        member1.put("memberId", memberId);
        member1.put("email", "hgd@gmail.com");
        member1.put("name", "홍길동");
        member1.put("phone", "010-1234-5678");

        members.put(memberId, member1);
    }

    // 1. 회원 정보 수정을 위한 핸들러 메서드 구현
    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id")long memberId, //  url에 있는 특정한 값을 뽑아오는 애너테이션 (http://localhost:8080/v1/members/1)
                                      @RequestParam("phone") String phone){ // requstParam = 사용자 입력 중 바디에 있는 값을 가져온다.
        members.get(memberId).replace("phone", phone);
        return new ResponseEntity(members, HttpStatus.OK);
    }


    // 2. 회원 정보 삭제를 위한 핸들러 메서드 구현
    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id")long memberId){
        members.remove(memberId);
        System.out.println("삭제성공");
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

}

```

``@PathVariable`` =  url에 있는 특정한 값을 뽑아오는 애너테이션 ``http://localhost:8080/v1/members/1``

``@RequestParam`` = 사용자 입력 중 바디에 있는 값을 뽑아주는 애너테이션

![](https://velog.velcdn.com/images/wish17/post/69fa55a9-61b3-4c04-b286-cf41b45d2029/image.png)


```java
package com.codestates.coffee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/coffees")
public class CoffeeController {
    private final Map<Long, Map<String, Object>> coffees = new HashMap<>();

    @PostConstruct
    public void init() {
        Map<String, Object> coffee1 = new HashMap<>();
        long coffeeId = 1L;
        coffee1.put("coffeeId", coffeeId);
        coffee1.put("korName", "바닐라 라떼");
        coffee1.put("engName", "Vanilla Latte");
        coffee1.put("price", 4500);

        coffees.put(coffeeId, coffee1);
    }

    //---------------- 여기서 부터 아래에 코드를 구현하세요! -------------------//
    // 1. 커피 정보 수정을 위한 핸들러 메서드 구현
    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchMember(@PathVariable("coffee-id")long coffeeId,
                                      @RequestParam("korName") String korName,
                                      @RequestParam("price") int price){
        coffees.get(coffeeId).replace("korName", "바닐라 빈 라떼");
        coffees.get(coffeeId).replace("price", 5000);
        return new ResponseEntity(coffees,HttpStatus.OK);
    }
    // 2. 커피 정보 삭제를 위한 핸들러 서드 구현

    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteMember(@PathVariable("coffee-id")long coffeeId){
        coffees.remove(coffeeId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
}

```

이렇게 코드 내에서 직접적으로 수정해줄 수도 있다.

![](https://velog.velcdn.com/images/wish17/post/522b0575-97b9-4507-b878-0c79a959993f/image.png)


![](https://velog.velcdn.com/images/wish17/post/0bef624d-3c3f-4309-a872-6d55df022774/image.png)



![](https://velog.velcdn.com/images/wish17/post/eb0b536b-a664-43e9-9c23-6a8a17149d20/image.png)

실습 중 [@RequestParam, @PathVariable차이](https://velog.io/@dongscholes/JavaSpringBoot-RequestParam-vs-PathVariable-%EC%93%B0%EC%9E%84%EC%83%88-%EC%82%AC%EC%9A%A9%EB%B2%95-%EC%B0%A8%EC%9D%B4%EC%A0%90)를 정확히 몰라서 고생했었다.

@PathVariable은 값을 하나만 받아올 수 있으므로, 여러 개 데이터를 받아올 때는 @RequestParam을 쓴다!

***

# DTO(Data Transfer Object)

> 엔터프라이즈 애플리케이션 아키텍처 패턴의 하나
- 요청 데이터를 하나의 객체로 전달 받는 역할을 해줌

## HTTP 요청/응답에서의 DTO(Data Transfer Object)

```java
@RestController
@RequestMapping("/v1/members")
public class MemberController {
    @PostMapping
    public ResponseEntity postMember(@RequestParam("email") String email,
                                     @RequestParam("name") String name,
                                     @RequestParam("phone") String phone) {
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("name", name);
        map.put("phone", phone);

        return new ResponseEntity<Map>(map, HttpStatus.CREATED);
    }

		...
		...
}                                   
```
기존에 사용하던 방식은 ``postMember()``에 파라미터로 추가되는 ``@RequestParam``의 개수가 계속해서 늘어난다.

```java
@RestController
@RequestMapping("/v1/members")
public class MemberController {
    @PostMapping
    public ResponseEntity postMember(MemberDto memberDto) {
        return new ResponseEntity<MemberDto>(memberDto, HttpStatus.CREATED);
    }

		...
		...
}
```

DTO 클래스를 적용하면 위와 같이 간단하게 가능
마찬가지로 필요한 데이터를 전달 받기 위해 데이터를 검증하는 **"유효성(Validation)검증"** 도 MemberDto 클래스에 작성해 기능분리가 가능해진다.

```java
public class MemberDto {
    @Email // email양식에 맞는지 검사해주는 애너테이션
    private String email;
    private String name;
    private String phone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
```

### DTO 클래스 적용을 위한 코드 리팩토링 절차

1. 정보를 전달 받을 DTO 클래스를 생성
- 각 멤버 변수에 해당하는 getter 메서드가 있어야 한다.
     - 현업에서는 아예 lombok이라는 라이브러리를 이용해서 getter/setter 메서드를 내부에서 자동으로 만들어 사용
2. 클라이언트 쪽에서 전달하는 요청 데이터를 @RequestParam 애너테이션으로 전달 받는 핸들러 메서드를 찾는다.
- Request Body가 필요한 핸들러는 HTTP POST, PATCH, PUT 같이 리소스의 추가나 변경이 발생할 때다. (GET은 필요 없음)
3. ``@RequestParam`` 쪽 코드를 DTO 클래스의 객체로 수정
4. Map 객체로 작성되어 있는 Response Body를 DTO 클래스의 객체로 변경

### DTO 클래스의 대표적인 단점

-  Controller 클래스가 늘어남에 따라 DTO 클래스가 두 배
 (ex. xxxxPostDto + xxxxPatchDto)씩 늘어나게 된다.
    - 공통된 멤버 변수의 추출 및 내부 클래스를 이용해서 어느 정도 개선 가능

### 사용기능,용어 정리

``@RequestBody``
- (JSON 형식의) Request Body를 MemberPostDto 클래스의 객체로 변환을 시켜주는 역할

``@ResponseBody``
- (JSON 형식의) Response Body를 클라이언트에게 전달하기 위해 DTO 클래스의 객체를 Response Body로 변환하는 역할

Spring MVC에서는 핸들러 메서드에 ``@ResponseBody`` 애너테이션이 붙거나 핸들러 메서드의 리턴 값이 ``ResponseEntity``일 경우, 내부적으로 ``HttpMessageConverter``가 동작하게 되어 응답 객체(여기서는 DTO 클래스의 객체)를 JSON 형식으로 바꿔준다.

``역직렬화(Deserialization)``
요청 받은 JSON 형식의 데이터를 DTO 같은 Java의 객체로 변환하는 것

``직렬화(Serialization)``
응답데이터를 전송하기 위해 DTO 같은 Java의 객체를 JSON 형식으로 변환하는 것

### 핵심 정리

- JSON 형식의 Request Body를 전달 받기 위해서는 DTO 객체에 ``@RequestBody`` 애너테이션을 붙여야 한다.
- Response Body를 JSON 형식으로 전달하기 위해서는 @ResponseBody 애너테이션을 메서드 앞에 붙여 주어야하지만 ResponseEntity 객체를 리턴 값으로 사용할 경우 @ResponseBody 를 생략할 수 있다.

***

## DTO 유효성 검증(Validation)

1차적으로 프론트엔드 쪽에서 유효성 검사를 진행하지만 자바스크립트로 전송되는 데이터는 브라우저의 개발자 도구를 사용해서 브레이크포인트(breakpoint)를 추가한 뒤에 얼마든지 그 값을 조작할 수 있기 때문에 서버 쪽에서 한번 더 유효성 검사를 진행해야 된다.

### 유효성 검증을 위한 의존 라이브러리 추가

DTO 클래스에 유효성 검증을 적용하기 위해서는 Spring Boot에서 지원하는 Starter가 필요하다.

 build.gradle 파일의 dependencies 항목에 ``'org.springframework.boot:spring-boot-starter-validation’``을 추가해야 한다.

### 기능정리

``@NotBlank(message = "~~~")``

- 정보가 비어있지 않은지를 검증
- null 값이나 공백(””), 스페이스(” “) 같은 값들을 모두 허용하지 않음
- 유효성 검증에 실패하면 @NotBlank 의 message 애트리뷰트에 지정한 문자열이 에러 메시지로 콘솔에 출력
    - 괄호 안쓰고 ``@NotBlank``만 쓰면 유효성 검증에 실패했을 때 에러 메시지가 콘솔에 출력

``@Email``

- 유효한 이메일 주소인지를 검증
- 유효성 검증에 실패하면 내장된 디폴트 에러 메시지가 콘솔에 출력

``@Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
            message = "~~~")``
- 휴대폰 정보가 정규표현식(Reqular Expression)에 매치되는 유효한 번호인지를 검증
- 유효성 검증에 실패하면 내장된 디폴트 에러 메시지가 콘솔에 출력


``@Valid``

- 유효성 검증 애너테이션이 추가된 DTO 클래스에서 유효성 검증 로직이 실행되게 하기 위해서는 아래와 같이 DTO 클래스에 @Valid 애너테이션을 추가해야 한다.
- 메서드 매개변수쪽(``@RequestBody``앞)에 사용


```java
@RestController
@RequestMapping("/v1/members")
public class MemberController {
    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberDto) {
        return new ResponseEntity<>(memberDto, HttpStatus.CREATED);
    }
		...
		...
}
```

``@Pattern()``
```java
@Pattern(regexp = "^\\S+(\\s?\\S+)*$", message = "~~~")
```

- 이름 정보가 비어있으면(null) 유효성 검증을 하지 않는다.
- 이름 정보가 비어 있지 않고(not null), 공백 문자열이라면 검증에 실패한다.
- 시작 문자가 공백이면 검증에 실패한다.
- 끝 문자가 공백이면 검증에 실패한다.
- 문자와 문자 사이 공백이 1개를 이상이면 검증에 실패한다.

``정규 표현식(Reqular Experssion)``

- ‘^’은 문자열의 시작을 의미
- ‘$’는 문자열의 끝을 의미
- ‘*’는 ‘*’ 앞에 평가할 대상이 0개 또는 1개 이상인지를 평가
- ‘\s’는 공백 문자열을 의미
- ‘\S’ 공백 문자열이 아닌 나머지 문자열을 의미
- ‘?’는 ‘?’ 앞에 평가할 대상이 0개 또는 1개인지를 의미
- ‘+’는 ‘+’ 앞에 평가할 대상이 1개인지를 의미

``@Min(1)``
- 1이상일 경우에만 유효성 검증 통과

``@Validated``

- @PathVariable이 추가된 변수에 유효성 검증이 정상적으로 수행되려면 추가해줘야하는 애너테이션
- class레벨(``@RequestMapping``뒤)에 사용


DTO 클래스의 유효성 검증을 위해서 사용한 위 기능들은 **Jakarta Bean Validation**이라는 유효성 검증을 위한 표준 스펙에서 지원하는 내장 애너테이션들이다.

> Jakarta Bean Validation
- 라이브러리처럼 사용할 수 있는 API가 아닌 스펙(사양, Specification) 자체다. ( 일종의 기능 명세를 의미 )
- Java Bean 스펙을 준수하는 Java 클래스라면 Jakarta Bean Validation의 애너테이션을 사용해서 유효성 검증을 할 수 있다.

### Custom Validator를 사용한 유효성 검증

Jakarta Bean Validation에 내장된(Built-in) 애너테이션 외에도 필요한 기능이 있다면 직접 [애너테이션을 정의](https://velog.io/@wish17/%EC%BD%94%EB%93%9C%EC%8A%A4%ED%85%8C%EC%9D%B4%EC%B8%A0-%EB%B0%B1%EC%97%94%EB%93%9C-%EB%B6%80%ED%8A%B8%EC%BA%A0%ED%94%84-167%EC%9D%BC%EC%B0%A8-Java-%EC%8B%AC%ED%99%94Effective#%EC%95%A0%EB%84%88%ED%85%8C%EC%9D%B4%EC%85%98annotation)해 사용할 수 있다.

Custom Validator를 구현하기 위한 절차

1. Custom Validator를 사용하기 위한 Custom Annotation을 정의한다.
2. 정의한 Custom Annotation에 바인딩 되는 Custom Validator를 구현한다.
3. 유효성 검증이 필요한 DTO 클래스의 멤버 변수에 Custom Annotation을 추가한다.




***

### DTO 유효성 검사실습

> NotSpace 인터페이스
- 공백을 허용하지 않는 Custom Annotation

```java
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {NotSpaceValidator.class}) // (1)
public @interface NotSpace {
    String message() default "공백이 아니어야 합니다"; // (2)
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
```
[@Target](https://velog.io/@wish17/%EC%BD%94%EB%93%9C%EC%8A%A4%ED%85%8C%EC%9D%B4%EC%B8%A0-%EB%B0%B1%EC%97%94%EB%93%9C-%EB%B6%80%ED%8A%B8%EC%BA%A0%ED%94%84-167%EC%9D%BC%EC%B0%A8-Java-%EC%8B%AC%ED%99%94Effective#target), [@Retention](https://velog.io/@wish17/%EC%BD%94%EB%93%9C%EC%8A%A4%ED%85%8C%EC%9D%B4%EC%B8%A0-%EB%B0%B1%EC%97%94%EB%93%9C-%EB%B6%80%ED%8A%B8%EC%BA%A0%ED%94%84-167%EC%9D%BC%EC%B0%A8-Java-%EC%8B%AC%ED%99%94Effective#retention)는 Section1내용 참고 

``@Target`` = 애너테이션을 적용할 “대상"을 지정하는 데 사용
``@Retention`` = 특정 애너테이션의 지속 시간을 결정하는 데 사용

``@Constraint`` (제약조건)
- ``@NotSpace`` 애너테이션이 멤버 변수에 추가되었을 때, 동작 할 Custom Validator를 연결해주는 용도로 사용했다고 생각하면 된다.


> NotSpaceValidator 클래스
-  Custom Validator 구현

```java
import org.springframework.util.StringUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotSpaceValidator implements ConstraintValidator<NotSpace, String> {

    @Override
    public void initialize(NotSpace constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || StringUtils.hasText(value);
    }
}
```

``<NotSpace, String>`` 제네릭에서 NotSpace는 CustomValidator와 매핑된 Custom Annotation(``@NotSpace``)을 의미하며, String은 Custom Annotation으로 검증할 대상 멤버 변수의 타입을 의미



> CoffeeController 클래스

```java
import com.codestates.coffee.CoffeePatchDto;
import com.codestates.coffee.CoffeePostDto;
import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
//import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;

@RestController
@RequestMapping("/v1/coffees")
@Validated // @Min() 등을 처리해주는 것
public class CoffeeController {

    @PostMapping
    public ResponseEntity postCoffee(@Valid @RequestBody CoffeePostDto coffeePostDto){

        return new ResponseEntity<>(coffeePostDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee(@PathVariable("coffee-id") @Positive long coffeeId,
                                      @Valid @RequestBody CoffeePatchDto coffeePatchDto){
        coffeePatchDto.setCoffeeId(coffeeId);

        return new ResponseEntity(coffeePatchDto, HttpStatus.OK);
    }

    @GetMapping("/{coffee-id}") // 클라이언트가 서버에 리소스를 조회할 때 사용하는 애너테이션
    public  ResponseEntity getCoffee(@PathVariable("coffee-id")long coffeeId){ // 특정 회원의 정보를 클라이언트 쪽에 제공하는 핸들러 메서드
        System.out.println("# coffeeId: " + coffeeId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers() { // 회원 목록을 클라이언트에게 제공하는 핸들러 메서드
        System.out.println("# get coffees");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

```

[@Valid와 @Validated](https://velog.io/@wish17/Valid%EC%99%80-Validated%EC%97%90-%EA%B4%80%ED%95%9C-%EA%B3%A0%EC%B0%B0)의 차이점에 대해 의문이 생겨 추가로 블로깅했다.

> CoffeePostDto 클래스

```java
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CoffeePostDto {
    @NotBlank
    private String korName;
    @NotBlank
    @Pattern(regexp = "^([a-zA-Z]+\\s?[a-zA-Z])+$") // 영어만 허용
    // [A]* : A가 0개 이상이다.
    // [A]+ : A가 1개 이상이다.
    // \\s? : 공백이 0개 or 1개다.
    // 즉, "^([a-zA-Z]+\\s?[a-zA-Z])*$"는 알파벳(몇개인지 상관x) + 공백 + 알파벳(몇개인지 상관x)이다.
    // n\d* : n 뒤에 숫자가 0개 이상이라는 의미. “n”, “n1”, “n123” 에 모두 매치된다.
    private String engName;
    @Range(min = 100, max= 50000)
    private Integer price;

    public String getKorName() {
        return korName;
    }

    public void setKorName(String korName) {
        this.korName = korName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

```

CoffeePostDto 클래스에서 영어만 허용하도록 ``@Pattern``을 사용하는 과정에서 정규표현식 때문에 고생 좀 했다.

> CoffeePatchDto 클래스

```java
package com.codestates.coffee;

import com.codestates.member.NotSpace;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.Pattern;

public class CoffeePatchDto {
    private long coffeeId;
    @NotSpace // null가능하게 해줘야 함
    // @Pattern(regexp ="^\\S+(\\s?\\S+)*$") // << 이거로 대신 써도 됨
    private String korName;
    @Pattern(regexp = "^([a-zA-Z]+\\s?[a-zA-Z])*$")
    private String engName;
    @Range(min = 100, max= 50000)
    private Integer price; // int로 하면 null이 안들어간다. 기본값인 0이 들어가기 때문에 range에 걸려서 선택적으로 사용 못함

    public long getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(long coffeeId) {
        this.coffeeId = coffeeId;
    }

    public String getKorName() {
        return korName;
    }

    public void setKorName(String korName) {
        this.korName = korName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}

```

price변수 유효성 검사에서 null을 수용할 수 잇게 int 대신 Integer를 써야 했다.


***
### 정규표현식 문법 정리

[정규표현식 확인 사이트](https://regexr.com/)

<table style="border-collapse: collapse; width: 100%; height: 183px;" border="1" data-ke-align="alignLeft" data-ke-style="style4">
<tbody>
<tr style="height: 20px;">
<td style="width: 20.5814%; height: 20px;">^</td>
<td style="width: 79.4186%; height: 20px;">문자열의 시작</td>
</tr>
<tr style="height: 20px;">
<td style="width: 20.5814%; height: 20px;">$</td>
<td style="width: 79.4186%; height: 20px;">문자열의 끝</td>
</tr>
<tr style="height: 17px;">
<td style="width: 20.5814%; height: 17px;">.</td>
<td style="width: 79.4186%; height: 17px;">임의의 한 문자</td>
</tr>
<tr style="height: 18px;">
<td style="width: 20.5814%; height: 18px;">*</td>
<td style="width: 79.4186%; height: 18px;">문자가 0번 이상 발생</td>
</tr>
<tr style="height: 18px;">
<td style="width: 20.5814%; height: 18px;">+</td>
<td style="width: 79.4186%; height: 18px;">문자가 1번 이상 발생</td>
</tr>
<tr style="height: 18px;">
<td style="width: 20.5814%; height: 18px;">?</td>
<td style="width: 79.4186%; height: 18px;">문자가 0번 혹은 1번 발생</td>
</tr>
<tr style="height: 18px;">
<td style="width: 20.5814%; height: 18px;">[ ]&nbsp;</td>
<td style="width: 79.4186%; height: 18px;">문자의 집합 범위를 나타냄<br>[0-9] : 숫자 (0부터 9)<br>[a-z] : 알파벳 (a부터 z)<br><br>앞에 ^가 나타나면 not을 의미</td>
</tr>
<tr style="height: 18px;">
<td style="width: 20.5814%; height: 18px;">{ }&nbsp;</td>
<td style="width: 79.4186%; height: 18px;">횟수 또는 범위를 의미</td>
</tr>
<tr style="height: 18px;">
<td style="width: 20.5814%; height: 18px;">( )&nbsp;</td>
<td style="width: 79.4186%; height: 18px;">소괄호 안의 문자를 하나의 문자로 인식</td>
</tr>
<tr style="height: 18px;">
<td style="width: 20.5814%; height: 18px;">|</td>
<td style="width: 79.4186%; height: 18px;">&nbsp;or 조건</td>
</tr>
<tr>
<td style="width: 20.5814%;">\</td>
<td style="width: 79.4186%;">확장 문자의 시작&nbsp;</td>
</tr>
<tr>
<td style="width: 20.5814%;">\b</td>
<td style="width: 79.4186%;">단어의 경계</td>
</tr>
<tr>
<td style="width: 20.5814%;">\B</td>
<td style="width: 79.4186%;">단어가 아닌 것의 경계</td>
</tr>
<tr>
<td style="width: 20.5814%;">\A</td>
<td style="width: 79.4186%;">입력의 시작부분</td>
</tr>
<tr>
<td style="width: 20.5814%;">\G</td>
<td style="width: 79.4186%;">이전 매치의 끝</td>
</tr>
<tr>
<td style="width: 20.5814%;">\Z</td>
<td style="width: 79.4186%;">입력의 끝이지만 종결자가 있는 경우</td>
</tr>
<tr>
<td style="width: 20.5814%;">\z</td>
<td style="width: 79.4186%;">입력의 끝</td>
</tr>
<tr>
<td style="width: 20.5814%;">\s</td>
<td style="width: 79.4186%;">공백문자</td>
</tr>
<tr>
<td style="width: 20.5814%;">\S</td>
<td style="width: 79.4186%;">공백문자가 아닌 나머지 문자</td>
</tr>
<tr>
<td style="width: 20.5814%;">\w</td>
<td style="width: 79.4186%;">알파벳이나 숫자</td>
</tr>
<tr>
<td style="width: 20.5814%;">\W</td>
<td style="width: 79.4186%;">알파벳이나 숫자를 제외한 문자</td>
</tr>
<tr>
<td style="width: 20.5814%;">\d</td>
<td style="width: 79.4186%;">[0-9]와 동일</td>
</tr>
<tr>
<td style="width: 20.5814%;">\D</td>
<td style="width: 79.4186%;">숫자를 제외한 모든 문자</td>
</tr>
</tbody>
</table>

***
###  java.validation 어노테이션 정리

<table style="border-collapse: collapse; width: 100%; height: 280px;" border="1" data-ke-style="style12" data-ke-align="alignLeft">
<tbody>
<tr style="height: 20px;">
<td style="width: 25.698%; height: 20px;">Anotation</td>
<td style="width: 74.302%; height: 20px;">제약조건</td>
</tr>
<tr style="height: 20px;">
<td style="width: 25.698%; height: 20px;">@NotNull</td>
<td style="width: 74.302%; height: 20px;">Null 불가</td>
</tr>
<tr style="height: 20px;">
<td style="width: 25.698%; height: 20px;">@Null</td>
<td style="width: 74.302%; height: 20px;">Null만 입력 가능</td>
</tr>
<tr>
<td style="width: 25.698%;">@NotEmpty</td>
<td style="width: 74.302%;">Null, 빈 문자열 불가</td>
</tr>
<tr>
<td style="width: 25.698%;">@NotBlank</td>
<td style="width: 74.302%;">Null, 빈 문자열, 스페이스만 있는 문자열 불가</td>
</tr>
<tr style="height: 20px;">
<td style="width: 25.698%; height: 20px;">@Size(min=,max=)</td>
<td style="width: 74.302%; height: 20px;">문자열, 배열등의 크기가 만족하는가?</td>
</tr>
<tr style="height: 20px;">
<td style="width: 25.698%; height: 20px;">@Pattern(regex=)</td>
<td style="width: 74.302%; height: 20px;">정규식을 만족하는가?</td>
</tr>
<tr style="height: 20px;">
<td style="width: 25.698%; height: 20px;">@Max(숫자)</td>
<td style="width: 74.302%; height: 20px;">지정 값 이하인가?</td>
</tr>
<tr style="height: 20px;">
<td style="width: 25.698%; height: 20px;">@Min(숫자)</td>
<td style="width: 74.302%; height: 20px;">지정 값 이상인가</td>
</tr>
<tr style="height: 20px;">
<td style="width: 25.698%; height: 20px;">@Future</td>
<td style="width: 74.302%; height: 20px;">현재 보다 미래인가?</td>
</tr>
<tr style="height: 20px;">
<td style="width: 25.698%; height: 20px;">@Past</td>
<td style="width: 74.302%; height: 20px;">현재 보다 과거인가?</td>
</tr>
<tr>
<td style="width: 25.698%;">@Positive</td>
<td style="width: 74.302%;">양수만 가능</td>
</tr>
<tr>
<td style="width: 25.698%;">@PositiveOrZero</td>
<td style="width: 74.302%;">양수와 0만 가능</td>
</tr>
<tr>
<td style="width: 25.698%;">@Negative</td>
<td style="width: 74.302%;">음수만 가능</td>
</tr>
<tr>
<td style="width: 25.698%;">@NegativeOrZero</td>
<td style="width: 74.302%;">음수와 0만 가능</td>
</tr>
<tr>
<td style="width: 25.698%;">@Email</td>
<td style="width: 74.302%;">이메일 형식만 가능</td>
</tr>
<tr style="height: 20px;">
<td style="width: 25.698%; height: 20px;">@Digits(integer=, fraction = )</td>
<td style="width: 74.302%; height: 20px;">대상 수가 지정된 정수와 소수 자리 수 보다 작은가?</td>
</tr>
<tr style="height: 20px;">
<td style="width: 25.698%; height: 20px;">@DecimalMax(value=)&nbsp;</td>
<td style="width: 74.302%; height: 20px;"><span>지정된 값(실수) 이하인가?</span></td>
</tr>
<tr style="height: 20px;">
<td style="width: 25.698%; height: 20px;"><span>@DecimalMin(value=)</span></td>
<td style="width: 74.302%; height: 20px;"><span>지정된 값(실수) 이상인가?</span></td>
</tr>
<tr style="height: 20px;">
<td style="width: 25.698%; height: 20px;"><span>@AssertFalse</span></td>
<td style="width: 74.302%; height: 20px;">false 인가?</td>
</tr>
<tr style="height: 20px;">
<td style="width: 25.698%; height: 20px;"><span>@AssertTrue</span></td>
<td style="width: 74.302%; height: 20px;">true 인가?</td>
</tr>
</tbody>
</table>

***
# [Spring MVC] 서비스 계층

> 서비스 계층
- API 계층에서 전달 받은 클라이언트의 요청 데이터를 기반으로 실질적인 비즈니스 요구사항을 처리하는 계층

## DI를 통한 서비스 계층 ↔ API 계층 연동

> API 계층에서 구현한 Controller 클래스가 서비스 계층의 Service 클래스와 메서드 호출을 통해 상호 작용한다는 것

### 개념정리

>** Service**
- 도메인 업무 영역을 구현하는 비즈니스 로직을 처리하는 것

> **도메인 엔티티(Entity) 클래스**
- 서비스 계층에서 데이터 액세스 계층과 연동하면서 비즈니스 로직을 처리하기 위해 필요한 데이터를 담는 역할을 하는 클래스
- API 계층에서 전달 받은 요청 데이터를 기반으로 서비스 계층에서 비즈니스 로직을 처리하기 위해 필요한 데이터를 데이터 액세스 계층으로부터 전달 받고, 비즈니스 로직을 처리한 후에는 결과 값을 다시 API 계층으로 리턴해주는 역할
- API계층에서 DTO클래스 같은 느낌

[![](https://velog.velcdn.com/images/wish17/post/ef05de2e-56d5-41e0-92d8-d05c3e5de2c7/image.png)](https://coco-log.tistory.com/94)



### 기능정리

``@Getter``, ``@Setter``

- getter/setter 메서드를 일일이 작성하는 수고를 덜어주는 편리한 유틸리티성 라이브러리
-  lombok이라는 라이브러리에서 제공하는 애너테이션

``@AllArgsConstructor``

- (모든 멤버 변수를 파라미터로 갖는) 생성자를 자동으로 생성

``@NoArgsConstructor``

- 파라미터가 없는 기본 생성자를 자동으로 생성


``@RestController``
- 해당 컨트롤러에서 return 하는 값을 그대로 클라이언트에게 전달해준다.
- 즉, 페이지가 아닌 데이터 자체를 반환할 때 사용하는 어노테이션
- Spring Bean 등록

``@Service``
- Spring에서 관리되는 객체임을 표시하기 위해 사용하는 애너테이션
- 비즈니스 로직이나 respository layer 호출하는 함수에 사용
- @Component 어노테이션과 거의 차이가 없지만 비즈니스 로직을 수행하는 서비스 레이어 클래스임을 나타낸다.
- Spring Bean 등록

> - Controller 클래스에 ``@RestController`` 애너테이션을 추가하면 Spring Bean으로 등록된다.
- Service 클래스에 ``@Service`` 애너테이션을 추가하면 Spring Bean으로 등록된다.
- 생성자 방식의 의존성주입(DI)은 생성자가 하나일 경우에는 ``@Autowired`` 애너테이션을 추가하지 않아도 DI가 적용된다.

***

### 실습적용

> Member 클래스

```java
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
```

> MemberService 클래스

```java
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

    }
}

```

> MemberController 클래스

```java
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

    public MemberController(MemberService memberService) { // 느슨한결합  // Spring이 애플리케이션 로드시 자동으로 주입해줌
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

```

현재 상태로는 2가지 문제점이 있다.
- MemberController의 핸들러 메서드가 DTO 클래스를 엔티티(Entity) 클래스로 변환하는 작업까지 도맡아서 하고 있다.
- 엔티티(Entity) 클래스의 객체를 클라이언트의 응답으로 전송함으로써 계층 간의 역할 분리가 이루어지지 않았다.

DTO 클래스와 엔티티(Entity) 클래스를 서로 변환해주는 매퍼(Mapper)를 만들어서 해결 가능하다.
***

## 매퍼(Mapper)를 이용한 DTO 클래스 ↔ 엔티티(Entity) 클래스 매핑
> Mapper적용 전
- DTO 클래스-> MemberController -> 엔티티(Entity)
- 엔티티(Entity) -> 응답


> Mapper적용
- DTO 클래스-> MemberMapper -> 엔티티(Entity)
- 엔티티(Entity) -> MemberMapper -> DTO 클래스 -> 응답

***

### Mapper적용 실습코드

> MemberResponseDto 클래스

```java
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponseDto { // 응답 데이터의 역할을 해주는 DTO 클래스
    private long memberId;

    private String email;

    private String name;

    private String phone;
}

```

> MemberMapper 클래스

```java
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

```

> MemberController 클래스

```java
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

```

List 타입변환을 잘못해서 오류가 났었는데 stream 메서드 import 잘못한줄 알고 헛짓 좀 했다.
기본인데... 타입 생각 항상 하자.

***

### MapStruct를 이용한 Mapper 자동 생성

- 위 Meeper 적용 실습과 같이 개발자가 일일이 수작업으로 매퍼(Mapper) 클래스를 만드는 것은 비효율적이다.

> MapStruct
- 매퍼(Mapper) 클래스를 자동으로 구현

MapStruct 기반의 매퍼(Mapper)를 자동 생성하기 위해서 아래와 같이 ``build.gradle`` 파일에 MapStruct 의존 라이브러리 추가를 해줘야 한다.
```java
dependencies {
	...
	...
	implementation 'org.mapstruct:mapstruct:1.4.2.Final'
	annotationProcessor 'org.mapstruct:mapstruct-processor:1.4.2.Final'
}
```

``@Mapper``
- 해당 인터페이스를 MapStruct의 매퍼 인터페이스로 정의
- 애트리뷰트로 componentModel = "spring"을 지정해주면 Spring의 Bean으로 등록이 된다.
    - ``@Mapper(componentModel = "spring")``


> 매퍼(Mapper) 인터페이스
-  MapStruct 기반의 매퍼(Mapper) 인터페이스 정의한거

```java
package com.codestates.section3week1.member.mapstruct.mapper;

import com.codestates.section3week1.member.Member;
import com.codestates.section3week1.member.MemberPatchDto;
import com.codestates.section3week1.member.MemberPostDto;
import com.codestates.section3week1.member.MemberResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto); // MemberPostDto를 Member로 변환
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto); // MemberPatchDto를 Member로 변환
    MemberResponseDto memberToMemberResponseDto(Member member); // Member를 MemberResponseDto로 변환
}

```

### DTO 클래스와 엔티티 클래스의 역할 분리가 필요한 이유

- 계층별 관심사의 분리
- 코드 구성의 단순화
- REST API 스펙의 독립성 확보

### 정리

- Mapper를 사용해서 DTO 클래스와 Entity 클래스 간의 관심사를 분리할 수 있다.
- Mapper를 개발자가 직접 구현하기 보다는 MapStruct 같은 매핑 라이브러리를 사용하는 것이 생산성 측면에서 더 나은 선택이다.

***

### 학습내용 복습

[CoffeeController 서비스계층 추가하기](https://github.com/wish9/section3-week1/commit/9f98ece16c7cbd491b15456f06b8837c8de6bd06)

위 과정을 한번 더 반복해서 최대한 참고하지 않고 coffee패키지에 적용해 봤다.

***

# [Spring MVC] 예외 처리

## @ExceptionHandler를 이용한 Controller 레벨에서의 예외 처리

[풀코드는 github링크](https://github.com/wish9/section3-week1/commit/d730982479856894e34b091bf5793bdf70a048b9) 참고

```java
// 클라이언트에서 핸들러 메서드에 요청을 전송했을 때
// 각 메서드의 맞는 유효한 데이터가 아니면 유효성 검증에 실패하고, ``MethodArgumentNotValidException``이 발생
@ExceptionHandler // ``MethodArgumentNotValidException``이 발생하면 전달해주는 애너테이션
public ResponseEntity handleException(MethodArgumentNotValidException e) { // 예외처리 메서드
	final List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
    // MethodArgumentNotValidException 객체(e)에서 getBindingResult().getFieldErrors()를 통해 발생한 에러 정보를 확인할 수 있다.

	return new ResponseEntity(fieldErrors, HttpStatus.BAD_REQUEST);
}
```

Controller 클래스에 위와 같이 ``MethodArgumentNotValidException``이 발생했을 때(유효하지 않은 요청일 때) ``@ExceptionHandler`` 애너테이션을 이용해 예외를 처리하는 메서드로 유효성 검증 실패의 원인을 가져올 수 있다.

그런데 위와 같이 하면 List(``fieldErrors``)에 필요없는 정보가 많이 담겨 있다.

``fieldErrors``를 편집할 클래스(``ErrorResponse`` 클래스)를 추가하면 클라이언트에 표기해줄 오류문의 가독성을 더 좋게할 수 있다.

[풀 코드](https://github.com/wish9/section3-week1/commit/24f1e93eb9f8a8baccb2cf3da343842f3a63acca)

```java
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private List<FieldError> fieldErrors;

    @Getter
    @AllArgsConstructor // 해당 객체 내에 있는 모든 변수들을 인수로 받는 생성자를 만들어냄
    public static class FieldError{ // ErrorResponse 클래스의 static 멤버 클래스

        private String field;
        private Object rejectedValue;
        private String reason;

//        public FieldError(String field, Object rejectedValue, String reason) { // << @AllArgsConstructor가 이걸 만들어 줌
//            this.field = field;
//            this.rejectedValue = rejectedValue;
//            this.reason = reason;
//        }
    }
}

```

```java
@ExceptionHandler
public ResponseEntity handleException(MethodArgumentNotValidException e) { // 예외처리 메서드
	final List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
    // MethodArgumentNotValidException 객체(e)에서 getBindingResult().getFieldErrors()를 통해 발생한 에러 정보를 확인할 수 있다.

	List<ErrorResponse.FieldError> errors = fieldErrors.stream()
                .map(error -> new ErrorResponse.FieldError(
                        error.getField(),
                        error.getRejectedValue(),
                        error.getDefaultMessage()))
                .collect(Collectors.toList());

	return new ResponseEntity(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
}
```

![](https://velog.velcdn.com/images/wish17/post/91848869-3a3b-461e-9829-09680e75ffb0/image.png)

### @ExceptionHandler의 단점

1. 각 Controller 클래스마다 코드 중복이 발생
	- 각각의 Controller 클래스에서 @ExceptionHandler 애너테이션을 사용하여 Request Body에 대한 유효성 검증 실패에 대한 에러 처리를 해야 됨.

2. 예외의 종류별로 ``@ExceptionHandler`` 를 추가한 에러 처리 핸들러 메서드를 만들어 줘야 한다.
	- Controller에서 처리해야 되는 예외(Exception)가 유효성 검증 실패에 대한 예외(MethodArgumentNotValidException)만 있는것이 아니다.

***

## @RestControllerAdvice를 이용한 예외처리

``@RestControllerAdvice``

- 예외를 클래스로 가져오는 애너테이션
- 예외 처리 **공통화**하는데 사용

```java
@RestControllerAdvice
public class GlobalExceptionAdvice {

	// 유효성 검증에 실패 예외(MethodArgumentNotValidException) 처리 메서드
    @ExceptionHandler
    public ResponseEntity handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        ~~~
        return ~~~;
    }

	// 제약 조건 위반예외(ConstraintViolationException) 처리 메서드
    @ExceptionHandler
    public ResponseEntity handleConstraintViolationException( 
            ConstraintViolationException e) {
		~~~
        return ~~~;
    }
}
```

위와 같이 클래스 하나에 오류처리를 몰아서 코드의 중복을 없앨 수 있다.

### 실습

[풀 코드](https://github.com/wish9/section3-week1/commit/c1c91bb0cf20c3a7fe53fd29bb3d657a5f6dfe85)

> GlobalExceptionAdvice 클래스
- 다양한 종류의 예외를 처리하는 메서드들을 모아둔 클래스

```java
import com.codestates.section3week1.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionAdvice { // 예외처리 클래스

    @ExceptionHandler // 예외 전달해주는 애너테이션
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) { // 유효성 검증에 실패 예외(MethodArgumentNotValidException) 처리 메서드
        final ErrorResponse response = ErrorResponse.of(e.getBindingResult());

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }


    // 핸들러 메서드의 URI 변수인 “/{member-id}”에 0이 넘어올 경우, ConstraintViolationException이 발생
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(
            ConstraintViolationException e) {
        final ErrorResponse response = ErrorResponse.of(e.getConstraintViolations());

        return response;
    }
}

```

@RestControllerAdvice 애너테이션을 사용하면 ResponseEntity로 래핑할 필요가 없다.
- ``@RestControllerAdvice``는 @ResponseBody가 추가로 붙어있어, 응답을 JSON으로 내려준다.


- ``ResponseEntity``를 사용하지 않아도 위 ``handleConstraintViolationException()``메서드와 같이 ``@ResponseStatus`` 애너테이션을 사용해 ``HttpStatus.BAD_REQUEST``도 포함시켜 응답을 보낼 수 있다.



> ErrorResponse 클래스
- 예외응답을 간결화하는 메서드를 각 예외 타입별로 멤버 클래스 형태로 정리해둔 클래스

```java
package com.codestates.section3week1.response;

import lombok.Getter;
import org.springframework.validation.BindingResult;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse { // 에러문 간략화해서 필요한 정보만 담는 용도의 클래스
    private List<FieldError> fieldErrors;
    private List<ConstraintViolationError> violationErrors;

    // ErrorResponse 생성자
    private ErrorResponse(List<FieldError> fieldErrors, List<ConstraintViolationError> violationErrors) {
        this.fieldErrors = fieldErrors;
        this.violationErrors = violationErrors;
    }

    // BindingResult에 대한 ErrorResponse 객체 생성
    public static ErrorResponse of(BindingResult bindingResult){
        return new ErrorResponse(FieldError.of1(bindingResult), null);
    }

    // Set<ConstraintViolation<?>> 객체에 대한 ErrorResponse 객체 생성
    // 메서드 오버로딩
    public static ErrorResponse of(Set<ConstraintViolation<?>> violations) {
        return new ErrorResponse(null, ConstraintViolationError.of2(violations));
    }



    @Getter
    public static class FieldError{ // ErrorResponse 클래스의 static 멤버 클래스

        private String field;
        private Object rejectedValue;
        private String reason;

        private FieldError(String field, Object rejectedValue, String reason) {
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        private static List<FieldError> of1(BindingResult bindingResult) { // MethodArgumentNotValidException = BindingResult
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();

            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }

    @Getter
    public static class ConstraintViolationError {
        private String propertyPath;
        private Object rejectedValue;
        private String reason;

        private ConstraintViolationError(String propertyPath, Object rejectedValue, String reason) {
            this.propertyPath = propertyPath;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        private static List<ConstraintViolationError> of2(Set<ConstraintViolation<?>> constraintViolations) {
            return constraintViolations.stream()
                    .map(constraintViolation -> new ConstraintViolationError(
                            constraintViolation.getPropertyPath().toString(),
                            constraintViolation.getInvalidValue().toString(),
                            constraintViolation.getMessage()))
                    .collect(Collectors.toList());
        }
    }
}

```


### MethodArgumentNotValidException와 BindingResult

``BindingResult`` 인터페스를 구현하고 ``Exception`` 클래스를 상속받은 ``BindException`` 클래스을 상속받은 클래스가 ``MethodArgumentNotValidException``이다.

대충 말하자면 ``MethodArgumentNotValidException``는 
MethodParameter객체 + BindingResult객체라고 할 수 있다.

여기서 원하는 에러문은 BindingResult에 있기 때문에 ``getBindingResult()`` 메소드를 이용해 MethodParameter객체를 버리고 가져온 것이다.

``BindingResult 인터페이스``
- 검증 오류가 발생할 경우 오류 내용을 보관하는 스프링 프레임워크에서 제공하는 객체

***

# 비즈니스 로직에대한 예외 처리

## 비즈니스적인 예외 던지기(throw) 및 예외 처리

### 개념정리

> 체크 예외
- 발생한 예외를 잡아서(catch) 체크한 후에 해당 예외를 복구 하든가 아니면 회피 하든가 등의 어떤 구체적인 처리를 해야 하는 예외

> 언체크 예외
- 잡아서(catch) 해당 예외에 대한 어떤 처리를 할 필요가 없는 예외
- ex) ``NullPointerException``, ``ArrayIndexOutOfBoundsException`` 등
 코드를 잘못 작성해서 발생하는 ``RuntimeException``을 상속한 예외들
- ``RuntimeException``을 이용해서 일부러 예외(Exception)를 만들어야 할 경우도 있다.

``@ResponseStatus`` = 고정된 Exception(예외)를 처리할 경우에 사용

``ResponseEntity`` = 다양한 유형의 Exception(예외)을 처리하고자 할 경우에 사용

### Custom Exception(예외) 만들기

1. 예외 상수를 정의
- Custom Exception에 사용할 **ExceptionCode**를 enum으로 정의

```java
import lombok.Getter;

public enum ExceptionCode { // Custom 예외 정의하기 - 예외 멘트(상수) 정의
    MEMBER_NOT_FOUND(404, "Member Not Found");
    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}

```

2. Custom Exception 정의

```java
import lombok.Getter;

public class BusinessLogicException extends RuntimeException{ // Custom Exception 정의
    @Getter
    private ExceptionCode exceptionCode;

    public BusinessLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}

```

이렇게 만든 Custon Exception을 아래와 같이 사용하면 된다.

```java
@Service
public class MemberService {
	
    ~~~
    
    public Member findMember(long memberId) {
		
        ~~~
        
        if(a==0 ~~~)
        throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
    }

}
```

```java
@RestControllerAdvice
public class GlobalExceptionAdvice {
	
    ~~~
    
    @ExceptionHandler
    public ResponseEntity handleBusinessLogicException(BusinessLogicException e) {

        return new ResponseEntity(HttpStatus.valueOf(e.getExceptionCode().getStatus()));
    }
}
```

***

## 실습

> ExceptionCode 클래스
- 커스텀 예외 상수 정의

```java
import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}

```

> BusinessLogicException 클래스
- 커스텀 예외 정의

```java
import lombok.Getter;

public class BusinessLogicException extends RuntimeException {
    @Getter
    private ExceptionCode exceptionCode;

    public BusinessLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}

```

> ErrorResponse 클래스
- 예외별 응답을 간략화하는 클래스

```java
@Getter
public class ErrorResponse {
    private List<FieldError> fieldErrors;
    private List<ConstraintViolationError> violationErrors;
    private ServiceError serviceErrors;
    private MethodNotAllowed methodErrors;
    private ExceptionError exceptionError;

    public ErrorResponse(List<FieldError> fieldErrors, List<ConstraintViolationError> violationErrors, ServiceError serviceErrors) {
        this.fieldErrors = fieldErrors;
        this.violationErrors = violationErrors;
        this.serviceErrors = serviceErrors;
    }

//    public ErrorResponse(List<FieldError> fieldErrors, List<ConstraintViolationError> violationErrors, List<MethodNotAllowed> methodErrors) {
//        this.fieldErrors = fieldErrors;
//        this.violationErrors = violationErrors;
//        this.methodErrors = methodErrors;
//    } // 생성자 매개변수 갯수 똑같이 오버로딩하면 안됨

    public ErrorResponse(List<FieldError> fieldErrors, List<ConstraintViolationError> violationErrors, ServiceError serviceErrors, MethodNotAllowed methodErrors) {
        this.fieldErrors = fieldErrors;
        this.violationErrors = violationErrors;
        this.serviceErrors = serviceErrors;
        this.methodErrors = methodErrors;
    }

    public ErrorResponse(List<FieldError> fieldErrors, List<ConstraintViolationError> violationErrors, ServiceError serviceErrors, MethodNotAllowed methodErrors, ExceptionError exceptionError) {
        this.fieldErrors = fieldErrors;
        this.violationErrors = violationErrors;
        this.serviceErrors = serviceErrors;
        this.methodErrors = methodErrors;
        this.exceptionError = exceptionError;
    }

    public static ErrorResponse of(BindingResult bindingResult) {
        return new ErrorResponse(FieldError.of1(bindingResult), null, null);
    }

    public static ErrorResponse of(Set<ConstraintViolation<?>> violations) {
        return new ErrorResponse(null, ConstraintViolationError.of2(violations), null);
    }

    public static ErrorResponse of(ExceptionCode exceptionCode){
       // return new ErrorResponse(null, null, new ArrayList<>((Collection) new ServiceError(exceptionCode.getStatus(), exceptionCode.getMessage())));
        return new ErrorResponse(null, null, ServiceError.of3(exceptionCode));
    }

    public static ErrorResponse of(HttpRequestMethodNotSupportedException e){
        return new ErrorResponse(null, null, null, MethodNotAllowed.of4(e));
    }

    public static ErrorResponse of(Exception e){
        return new ErrorResponse(null, null, null, null, ExceptionError.of5(e));
    }



    @Getter
    private static class FieldError {
        private String field;
        private Object rejectedValue;
        private String reason;

        private FieldError(String field, Object rejectedValue, String reason) {
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        private static List<FieldError> of1(BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors =
                    bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ?
                                    "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }

    @Getter
    private static class ConstraintViolationError {
        private String propertyPath;
        private Object rejectedValue;
        private String reason;

        private ConstraintViolationError(String propertyPath, Object rejectedValue,
                                         String reason) {
            this.propertyPath = propertyPath;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        private static List<ConstraintViolationError> of2(
                Set<ConstraintViolation<?>> constraintViolations) {
            return constraintViolations.stream()
                    .map(constraintViolation -> new ConstraintViolationError(
                            constraintViolation.getPropertyPath().toString(),
                            constraintViolation.getInvalidValue().toString(),
                            constraintViolation.getMessage()
                    )).collect(Collectors.toList());
        }
    }

    @Getter
    private static class ServiceError{
        private int status;
        private String message;

        public ServiceError(int status, String message) {
            this.status = status;
            this.message = message;

        }
        
        
        private static ServiceError of3(ExceptionCode exceptionCode){

            return new ServiceError(exceptionCode.getStatus(), exceptionCode.getMessage());

        }

    }

    @Getter
    private static class MethodNotAllowed{
        private int status;
        private String message;

        public MethodNotAllowed(int status, String message) {
            this.status = status;
            this.message = message;
        }

        private static MethodNotAllowed of4(HttpRequestMethodNotSupportedException e){
            return new MethodNotAllowed(ExceptionCode.METHOD_NOT_ALLOWED.getStatus(), ExceptionCode.METHOD_NOT_ALLOWED.getMessage());
        }
    }

    @Getter
    private static class ExceptionError{
        private int status;
        private String message;

        public ExceptionError(int status, String message) {
            this.status = status;
            this.message = message;
        }

        private static ExceptionError of5(Exception e){
            return new ExceptionError(ExceptionCode.INTERNAL_SERVER_ERROR.getStatus(), ExceptionCode.INTERNAL_SERVER_ERROR.getMessage());
        }
    }
}
```

아래 캡쳐본과 같이 발생하지 않은 예외도 확인가능하게 하려고 위와 같이 코드를 작성했지만 새로운 타입의 예외가 추가됨에 따라 생성자, 메서드, 멤버클래스 등 추가되는 코드의 양이 너무 많아진다.

멤버클래스를 사용하지 않으면 더 간단하게 메소드만으로 구현가능할 것 같다.

![](https://velog.velcdn.com/images/wish17/post/0302f430-e257-4fa4-8e56-eb9c47bb3abc/image.png)


> GlobalExceptionAdvice 클래스
- 예외별 응답을 클라이언트에 보내주는 클래스

```java
@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        final ErrorResponse response = ErrorResponse.of(e.getBindingResult());

        return response;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(
            ConstraintViolationException e) {
        final ErrorResponse response = ErrorResponse.of(e.getConstraintViolations());

        return response;
    }

    @ExceptionHandler
    public ResponseEntity handleBusinessLogicException(BusinessLogicException e) {
        System.out.println(e.getExceptionCode().getStatus());
        System.out.println(e.getMessage());

        final ErrorResponse response = ErrorResponse.of(e.getExceptionCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getExceptionCode().getStatus()));
    }

    @ExceptionHandler
    public ResponseEntity handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        final ErrorResponse response = ErrorResponse.of(e);

        return new ResponseEntity(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler
    public ResponseEntity handleException(Exception e){
        final ErrorResponse response = ErrorResponse.of(e);

        return new ResponseEntity(response, HttpStatus.METHOD_NOT_ALLOWED);
    }
}

```

### 결과

> 없는 멤버요소를 찾으려 할 때
![](https://velog.velcdn.com/images/wish17/post/f986723a-ae4e-4f8a-98ba-bcd53830fc17/image.png)



> 잘못된 형식의 요청을 보낼 때
- HttpRequestMethodNotSupportedException 예외
![](https://velog.velcdn.com/images/wish17/post/08e35261-eacc-4c82-b61e-4e3ab91b0b1f/image.png)



> 서버 코드 오류일 떄
- ``NullpointerException``과 같은 ``Exception``예외들
![](https://velog.velcdn.com/images/wish17/post/c3585bb2-e58d-4553-8890-482726c8a39e/image.png)

이후에도 아래와 같은 과정을 통해 최적화(?)를 해봤다.
- 필요없는 생성자 삭제
- 일부 null응답하는 예외 클라이언트에서 안보이게하기

[최적화(?) 코드](https://github.com/wish9/section3-week1/commits/master)는 링크를 참조
(풀코드는 아래 빨간 박스안에 있는 3가지 버전에서 확인 가능)

![](https://velog.velcdn.com/images/wish17/post/6772c4af-a859-45c6-a280-29cd92f084b3/image.png)

최적화(?)를 통해 아래 캡쳐본과 같이 클라이언트에서 나오는 출력을 바꿔봤다.
![](https://velog.velcdn.com/images/wish17/post/8f73e5b7-a06b-4f6a-abbf-35c1c814b632/image.png)

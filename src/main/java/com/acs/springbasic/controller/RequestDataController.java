package com.acs.springbasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acs.springbasic.dto.Validation;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@RestController
// http://localhost:4000/request-data/**
@RequestMapping("/request-data")
public class RequestDataController {
    
    //# @RequestParam() : 
    // - GET, DELETE 처럼 Request Body가 존재하지 않고, 
    //   URL로 데이터를 전송하는 메서드에서 Query String 방식으로 
    //   데이터를 가져오기 위해 사용하는 어노테이션

    // GET http://localhost:4000/request-data/request-param
    @GetMapping("/request-param")
    // http://localhost:4000/request-data/request-param?name=홍길동&age=30
    public String requestParam(
        // @RequestParam(name="name") String name,
        @RequestParam("name") String name,
        @RequestParam(name="age", required=false) Integer age
    ) {
        return "이름 : " + name + " 나이 : " + age;
    };

    //# @PathVariable() :
    // 모든 HTTP 메서드에서 URL의 특정 패턴에 따라 데이터를 추출 (URL에서 추출하는 것임)
    // GET http://localhost:4000/request-data/path-variable/받고싶어값~/하나더
    // 기능을 기준으로 이름 설정 (데이터베이스 기준 X)
    @GetMapping({
        "/path-variable/{var}/{str}",
        "/path-variable/{var}/",
        "/path-variable/{var}"
    })
    public String pathVariable(
        @PathVariable(name="var") String var, 
        @PathVariable(name="str", required=false) String str 
    ) {
        return "읽은 경로 변수 : " + var + ", " + str;
    };

    //! 주의
    // 경로 변수를 사용하여 URL 패턴을 작성할 때
    // 겹치는 패턴이 존재하는지 잘 확인해야 함
    @GetMapping("/path-variable/other") // "/path-variable/{var}" 겹침
    public String otherPathVariable() {
        return "other 메서드 호출";
    };

    // /path-variable/another/another : 밑에 두 개가 겹쳐져서 에러!

    @GetMapping("/path-variable/{var}/another")
    public String anotherPathVariable1(
        @PathVariable("var") String var
    ) {
        return "another1";
    };

    @GetMapping("/path-variable/another/{var}")
    public String anotherPathVariable2(
        @PathVariable("var") String var
    ) {
        return "another2";
    };

    //# @RequestBody() :
    // - POST, PUT, PATCH 처럼 Request Body로 데이터를 전송하는 메서드에서 데이터를 읽기 위한 방법
    //& 가끔 문법이 맞는데 에러 뜰 때 전체 복사 후 다시 붙여넣고, 리 컴파일
    @PostMapping("/request-body")
    public String requestBody(
        // @RequestBody String requestBody
        @RequestBody SampleDto requestBody
    ) {
        return "Request Body data : " + requestBody.getName() + ", " + requestBody.getAge();
        // 그냥은 주소 참조값만 나옴, 그래서 requestBody.getName(), requestBody.getAge() 이렇게 확인!
    };

    @PostMapping("/validation")
    public String validation(
        @RequestBody @Valid Validation requestBody
        // @Valid : 검사할 곳에 지정해야 함!
    ) {
        return "정상";
    };

}
//# DTO(Data Transfer Object) :
// - 데이터를 서로 다른 계층간에 전송하기 위한 객체
// - 캡슐화가 되어 있음, 비즈니스 로직은 포함하지 않고, 'private 필드'와 '생성자', 'getter', 'setter'만 존재
// - JSON 형식으로 받을 때 사용
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class SampleDto {
    
    private String name;
    private int age;

}

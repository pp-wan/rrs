package com.hello.review.api.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/response")
public class TestResponseApi {

    @GetMapping("string")
    public String stringResponse() {
        return "This is String";
    }

    @GetMapping("json")
    public Person jsonResponse(){
        return new Person("TEST",30);
    }

    static class Person{

        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }
}

// Person 클래스에 Getter 메서드가 있어야지 스프링에서 JSON 라이브러리를 사용하여
// 객체의 필드 값을 가져갈 수 있도록 Getter 메서드를 제공하는 것이 필요하다.
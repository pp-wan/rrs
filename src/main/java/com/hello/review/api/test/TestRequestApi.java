package com.hello.review.api.test;

import jdk.jfr.DataAmount;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestRequestApi {

    @GetMapping("/test/param")
    public String testRequestParam(@RequestParam("name") String a,
                                   @RequestParam("age") Integer b) {

        return "my name is " + a + ".   " + "age : " + b;
    }

    @GetMapping("/test/path/{name}/{age}")
    public String requestPathVariable(
            @PathVariable("name") String name,
            @PathVariable Integer age) {
        return "TEST Request Path Variable" + " " + name + " " + age;
    }

    @GetMapping("/test/body")
    public String requestBody(
            @RequestBody Person person
    ) {
        return "TEST RequestBody " + person.name + " " + person.age;
    }


    static class Person {
        private String name;
        private String age;

        public Person(String name, String age) {
            this.name = name;
            this.age = age;
        }
    }
}

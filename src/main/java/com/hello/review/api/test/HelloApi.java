package com.hello.review.api.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApi {

    @GetMapping("/hello/world")
    public String helloApi() {
        return "Hello world!";
    }
}

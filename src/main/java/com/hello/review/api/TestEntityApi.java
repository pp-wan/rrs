package com.hello.review.api;


import com.hello.review.service.TestService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class TestEntityApi {

    private final TestService testService;

    @PostMapping("/test/entity/create")
    public void createTestEntity(
            @RequestBody CreateTestEntityRequest testEntityRequest
    ) {
        testService.create(testEntityRequest.getName(), testEntityRequest.getAge());
    }

    @PutMapping("/test/entity/{id}")
    public void putTestEntity(
            @PathVariable Long id,
            @RequestBody CreateTestEntityRequest testEntityRequest
    ) {
        testService.update(id, testEntityRequest.getName(), testEntityRequest.getAge());
    }

    @DeleteMapping("/test/entity/{id}")
    public void deleteTestEntity(@PathVariable Long id) {
        testService.delete(id);
    }


    @Data
    @AllArgsConstructor
    public static class CreateTestEntityRequest  {
         private final String name;
         private final Integer age;
    }
}

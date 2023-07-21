package com.hello.review.service;

import com.hello.review.model.TestEntity;
import com.hello.review.repository.TestRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TestService {

    private final TestRepository testRepository;

    public void create(String name, Integer age) {
        TestEntity testEntity = new TestEntity(name, age);
        testRepository.save(testEntity);
    }

    public void update(Long id, String name, Integer age) {
        Optional<TestEntity> findOne = testRepository.findById(id);
        TestEntity testEntity = findOne.orElseThrow();
        testEntity.changeNameAndAge(name, age);
        testRepository.save(testEntity);
    }

    public void delete(Long id) {
        Optional<TestEntity> findOne = testRepository.findById(id);
        TestEntity testEntity = findOne.orElseThrow();
        testRepository.delete(testEntity);
    }

    public List<TestEntity> findAllByNameByJPA(String name) {
        return testRepository.findAllByName(name);
    }

    public List<TestEntity> findAllByNameByQuerydsl(String name) {
        return testRepository.findAllByNameByQuerydsl(name);
    }

}

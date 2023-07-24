package com.hello.review.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "restaurant")
@Entity
public class RestaurantEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private ZonedDateTime created_at;

    private ZonedDateTime updated_at;


    public void changeNameAndAddress(String name, String address) {
        this.name = name;
        this.address = address;
        this.updated_at = ZonedDateTime.now();
    }
}

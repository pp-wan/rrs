package com.hello.review.api;

import com.hello.review.api.request.CreateAndEditRestaurantRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestaurantApi {

    //Todo 맛집 리스트 가져오기
    @GetMapping("/restaurants")
    public String getRestaurants() {
        return "This is getRestaurants";
    }

    //Todo 맛집 정보 가져오기
    @GetMapping("/restaurant/{restaurantId}")
    public String getRestaurant(
            @PathVariable Long restaurantId
    ) {
        return "This is getRestaurant, " + restaurantId;
    }

    //Todo 맛집 생성
    @PostMapping("/restaurant")
    public String createRestaurant(
            @RequestBody CreateAndEditRestaurantRequest request
            ) {
        return "This is createRestaurant, name=" + request.getName() + " address=" + request.getAddress() + " , menu[0].name=" + request.getMenus().get(0).getName() + " , menu[0].price=" + request.getMenus().get(0).getPrice();
    }

    //Todo 맛집 수정
    @PutMapping("/restaurant/{restaurantId}")
    public String editRestaurant(
            @PathVariable Long restaurantId,
            @RequestBody CreateAndEditRestaurantRequest request
    ) {
        return "This is editRestaurant, " + restaurantId + " name=" + request.getName() + " address=" + request.getAddress();
    }

    //Todo 맛집 삭제
    @DeleteMapping("/restaurant/{restaurantId}")
    public String deleteRestaurant(
            @PathVariable Long restaurantId
    ) {
        return "This is deleteRestaurant, " + restaurantId;
    }

}

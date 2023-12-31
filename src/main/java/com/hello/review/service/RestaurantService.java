package com.hello.review.service;

import com.hello.review.api.request.CreateAndEditRestaurantRequest;
import com.hello.review.api.response.RestaurantDetailView;
import com.hello.review.api.response.RestaurantView;
import com.hello.review.model.MenuEntity;
import com.hello.review.model.RestaurantEntity;
import com.hello.review.repository.MenuRepository;
import com.hello.review.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;



@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final MenuRepository menuRepository;

    @Transactional
    public RestaurantEntity createRestaurant(
            CreateAndEditRestaurantRequest request
    ) {
        RestaurantEntity restaurant = RestaurantEntity.builder()
                .name(request.getName())
                .address(request.getAddress())
                .created_at(ZonedDateTime.now())
                .updated_at(ZonedDateTime.now())
                .build();

        restaurantRepository.save(restaurant);

        request.getMenus().forEach((menu)->{
            MenuEntity menuEntity = MenuEntity.builder()
                    .restaurantId(restaurant.getId())
                    .name(menu.getName())
                    .price(menu.getPrice())
                    .created_at(ZonedDateTime.now())
                    .update_at(ZonedDateTime.now())
                    .build();

            menuRepository.save(menuEntity);
        });

        return restaurant;
    }

    @Transactional
    public void editRestaurant(
            Long restaurantId,
            CreateAndEditRestaurantRequest request
    ) {
        //레스토랑 데이터 불러오기
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RuntimeException("없는 레스토랑 입니다."));

        //레스토랑 이름 및 주소 변경
        restaurant.changeNameAndAddress(request.getName(),request.getAddress());

        //레스토랑 정보 저장
        restaurantRepository.save(restaurant);

        //전체 메뉴 삭제
        List<MenuEntity> menus = menuRepository.findAllByRestaurantId(restaurantId);
        menuRepository.deleteAll(menus);

        request.getMenus().forEach((menu) -> {
            MenuEntity menuEntity = MenuEntity.builder()
                    .restaurantId(restaurantId)
                    .name(menu.getName())
                    .price(menu.getPrice())
                    .created_at(ZonedDateTime.now())
                    .update_at(ZonedDateTime.now())
                    .build();

            menuRepository.save(menuEntity);
        });
    }

    @Transactional
    public void deleteRestaurant(Long restaurantId) {
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId).orElseThrow();

        restaurantRepository.delete(restaurant);

        List<MenuEntity> menus = menuRepository.findAllByRestaurantId(restaurantId);
        menuRepository.deleteAll(menus);

    }

    @Transactional(readOnly = true)
    public List<RestaurantView> getAllRestaurants() {
        List<RestaurantEntity> restaurants = restaurantRepository.findAll();

        return restaurants.stream().map((restaurant) -> RestaurantView.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .created_at(restaurant.getCreated_at())
                .updated_at(restaurant.getUpdated_at())
                .build()).toList();

    }

    @Transactional(readOnly = true)
    public RestaurantDetailView getRestaurantDetail(Long restaurantId) {
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId).orElseThrow();
        List<MenuEntity> menus = menuRepository.findAllByRestaurantId(restaurantId);

        return RestaurantDetailView.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .created_at(restaurant.getCreated_at())
                .updated_at(restaurant.getUpdated_at())
                .menus(
                        menus.stream().map(menuEntity -> RestaurantDetailView.Menu.builder()
                                .id(menuEntity.getId())
                                .name(menuEntity.getName())
                                .price(menuEntity.getPrice())
                                .created_at(menuEntity.getCreated_at())
                                .updated_at(menuEntity.getUpdate_at())
                                .build()
                        ).toList()
                )
                .build();
    }
}

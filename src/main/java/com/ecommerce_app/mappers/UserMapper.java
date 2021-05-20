package com.ecommerce_app.mappers;

import com.ecommerce_app.domain.entities.User;
import com.ecommerce_app.dtos.UserRequest;
import com.ecommerce_app.dtos.UserResponse;

public class UserMapper {

    public static UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .deliveryAddress(user.getDeliveryAddress())
                .email(user.getEmail())
                .basket(user.getBasket())
                .build();
    }

    public static User mapRequestToEntity(UserRequest userRequest) {
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .deliveryAddress(userRequest.getDeliveryAddress())
                .email(userRequest.getEmail())
                .build();
    }
}

package com.ecommerce_app.services;

import com.ecommerce_app.dtos.UserRequest;
import com.ecommerce_app.dtos.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getUsers();

    UserResponse getUser(Long id);

    UserResponse addUser(UserRequest userRequest);

    UserResponse updateUser(Long id, UserRequest userRequest);

    void deleteUser(Long id);
}

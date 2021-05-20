package com.ecommerce_app.services.impl;

import com.ecommerce_app.controllers.exceptions.ResourceNotFoundException;
import com.ecommerce_app.controllers.exceptions.ResourceNotUniqueException;
import com.ecommerce_app.domain.entities.User;
import com.ecommerce_app.domain.repositories.UserRepository;
import com.ecommerce_app.dtos.UserRequest;
import com.ecommerce_app.dtos.UserResponse;
import com.ecommerce_app.mappers.UserMapper;
import com.ecommerce_app.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::mapToUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUser(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::mapToUserResponse)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not found"));
    }

    @Transactional
    @Override
    public UserResponse addUser(UserRequest userRequest) {
        validateUserEmailExists(userRequest);
        User user = UserMapper.mapRequestToEntity(userRequest);
        userRepository.save(user);
        return UserMapper.mapToUserResponse(user);
    }

    @Transactional
    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                .map(userFromDb -> {
                    userFromDb.setFirstName(userRequest.getFirstName());
                    userFromDb.setLastName(userRequest.getLastName());
                    userFromDb.setDeliveryAddress(userRequest.getDeliveryAddress());
                    userFromDb.setEmail(userRequest.getEmail());
                    return userRepository.save(userFromDb);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not found"));

        return UserMapper.mapToUserResponse(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not found"));
        userRepository.delete(user);
    }

    private void validateUserEmailExists(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new ResourceNotUniqueException("User is already exist");
        }
    }
}

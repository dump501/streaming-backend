package com.dump501.streamingapp.controller;

import com.dump501.streamingapp.api.UserApi;
import com.dump501.streamingapp.dto.UserDto;
import com.dump501.streamingapp.dto.UserRequest;
import com.dump501.streamingapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;


    @Override
    public ResponseEntity<UserDto> createUser(UserRequest userRequest) {
        return ResponseEntity.ok(userService.create(userRequest));
    }

    @Override
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getAll());
    }
}

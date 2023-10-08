package com.dump501.streamingapp.controller;

import com.dump501.streamingapp.api.UserApi;
import com.dump501.streamingapp.dto.UserDto;
import com.dump501.streamingapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;

    @Override
    public ResponseEntity<UserDto> createUser(UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }
}

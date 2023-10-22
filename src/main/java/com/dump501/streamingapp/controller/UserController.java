package com.dump501.streamingapp.controller;

import com.dump501.streamingapp.api.UserApi;
import com.dump501.streamingapp.dto.UserDto;
import com.dump501.streamingapp.dto.UserStore;
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
    public ResponseEntity<UserDto> createUser(UserStore userStore) {
        return ResponseEntity.ok(userService.create(userStore));
    }

    @Override
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getAll());
    }
}

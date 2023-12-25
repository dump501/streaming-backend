package com.dump501.streamingapp.controller;

import com.dump501.streamingapp.api.AuthApi;
import com.dump501.streamingapp.dto.LoginRequest;
import com.dump501.streamingapp.dto.LoginResponse;
import com.dump501.streamingapp.dto.RegisterRequest;
import com.dump501.streamingapp.dto.UserDto;
import com.dump501.streamingapp.model.User;
import com.dump501.streamingapp.service.AuthenticationService;
import com.dump501.streamingapp.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@Log4j2
public class AuthController implements AuthApi {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        User user = authenticationService.authenticate(loginRequest);

        String jwtToken = jwtService.generateToken(user);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUuid(user.getUuid());
        loginResponse.setName(user.getName());
        loginResponse.setEmail(user.getEmail());
        loginResponse.setBio(user.getBio());
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(System.currentTimeMillis() + jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }

    @Override
    public ResponseEntity<UserDto> register(@RequestBody RegisterRequest registerRequest){
        UserDto user = authenticationService.register(registerRequest);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        User user = authenticationService.authenticate(loginRequest);

        String jwtToken = jwtService.generateToken(user);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setName(user.getName());
        loginResponse.setEmail(user.getEmail());
        loginResponse.setBio(user.getBio());
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn((int) jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }

    @Override
    public ResponseEntity<UserDto> register(@RequestBody RegisterRequest registerRequest){
        UserDto user = authenticationService.register(registerRequest);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}

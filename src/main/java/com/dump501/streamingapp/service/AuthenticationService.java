package com.dump501.streamingapp.service;

import com.dump501.streamingapp.dto.LoginRequest;
import com.dump501.streamingapp.dto.LoginResponse;
import com.dump501.streamingapp.dto.RegisterRequest;
import com.dump501.streamingapp.dto.UserDto;
import com.dump501.streamingapp.mapper.UserMapper;
import com.dump501.streamingapp.model.User;
import com.dump501.streamingapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public UserDto register(RegisterRequest registerRequest){
        User user = new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        return userMapper.toDto(userRepository.save(user));
    }

    public User authenticate(LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        return userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow();
    }
}

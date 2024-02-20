package com.dump501.streamingapp.service;

import com.dump501.streamingapp.dto.UserDto;
import com.dump501.streamingapp.dto.UserRequest;
import com.dump501.streamingapp.mapper.UserMapper;
import com.dump501.streamingapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserDto create(UserRequest userRequest){
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userRequest)));
    }

    public List<UserDto> getAll() {
        return  userMapper.toDto(userRepository.findAll());
    }

    public UserDto findById(UUID uuid){

        return userMapper.toDto(userRepository.findById(uuid).orElse(null));
    }
}

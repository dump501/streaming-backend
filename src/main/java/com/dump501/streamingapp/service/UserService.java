package com.dump501.streamingapp.service;

import com.dump501.streamingapp.dto.UserDto;
import com.dump501.streamingapp.mapper.UserMapper;
import com.dump501.streamingapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserDto create(UserDto userDto){
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
    }

    public List<UserDto> getAll() {
        return  userMapper.toDto(userRepository.findAll());
    }
}

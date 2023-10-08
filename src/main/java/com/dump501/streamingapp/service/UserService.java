package com.dump501.streamingapp.service;

import com.dump501.streamingapp.dto.UserDto;
import com.dump501.streamingapp.mapper.UserMapper;
import com.dump501.streamingapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserDto createUser(UserDto userDto){
        return userMapper.entityToDto(userRepository.save(userMapper.dtoToEntity(userDto)));
    }
}

package com.dump501.streamingapp.mapper;

import com.dump501.streamingapp.dto.UserDto;
import com.dump501.streamingapp.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto entityToDto(User user);
    User dtoToEntity(UserDto userDto);
}

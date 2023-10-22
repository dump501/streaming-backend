package com.dump501.streamingapp.mapper;

import com.dump501.streamingapp.dto.UserDto;
import com.dump501.streamingapp.dto.UserStore;
import com.dump501.streamingapp.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
    User toEntity(UserStore userStore);
    List<UserDto> toDto(List<User> users);
    List<User> toEntity(List<UserDto> userDtos);
}
